package com.ecommerce.ecommerce_api.repository;

import com.ecommerce.ecommerce_api.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository <Category, Long> {

    List<Category> findAll();
    Optional<Category> findById(Long id);
}
