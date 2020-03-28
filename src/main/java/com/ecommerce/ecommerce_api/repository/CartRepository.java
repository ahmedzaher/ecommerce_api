package com.ecommerce.ecommerce_api.repository;

import com.ecommerce.ecommerce_api.model.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart, Long> {

    Optional<Cart> findById(Long cartId);

    @Query("FROM Cart c WHERE c.user.id = :userId")
    Optional<Cart> getUserCart(@Param("userId") Long userId);
}
