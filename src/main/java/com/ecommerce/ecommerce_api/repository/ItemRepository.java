package com.ecommerce.ecommerce_api.repository;

import com.ecommerce.ecommerce_api.model.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends CrudRepository<Item, Long> {

    List<Item> findAll();
    Optional<Item> findById(Long id);
}
