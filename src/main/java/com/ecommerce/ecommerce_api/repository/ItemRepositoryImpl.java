package com.ecommerce.ecommerce_api.repository;


import com.ecommerce.ecommerce_api.dto.ItemFilterDto;
import com.ecommerce.ecommerce_api.model.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemRepositoryImpl implements ItemRepositoryCustom {

    EntityManager entityManager;

    public ItemRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Tuple> searchItems(ItemFilterDto filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        Root<Item> root = criteriaQuery.from(Item.class);

        TypedQuery<Tuple> typedQuery = entityManager.createQuery(
                criteriaQuery
                        .select(criteriaBuilder.tuple(root.get("id"), root.get("name"), root.get("price")))
                        .where(getItemSearchPredicate(filter, criteriaBuilder, root))
        )
                .setFirstResult(filter.getOffset())
                .setMaxResults(filter.getPageSize());

        return typedQuery.getResultList();
    }

    private static Predicate getItemSearchPredicate(ItemFilterDto filter, CriteriaBuilder criteriaBuilder,
                                                    Root<Item> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getName() != null) {
            predicates.add(
                    criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("name")),
                            '%' + filter.getName().toLowerCase() + '%'
                    )
            );
        }
        if (filter.getMinPrice() != null && filter.getMaxPrice() != null) {
            predicates.add(
                    criteriaBuilder.between(root.get("price"),
                            filter.getMinPrice(), filter.getMaxPrice()));
        } else if (filter.getMinPrice() != null) {
            predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(root.get("price"), filter.getMinPrice()));
        } else if (filter.getMaxPrice() != null) {
            predicates.add(
                    criteriaBuilder.lessThanOrEqualTo(root.get("price"), filter.getMaxPrice()));
        }
        if(filter.getAvailable() != null) {
            predicates.add(
                    criteriaBuilder.isTrue(root.get("available")));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
