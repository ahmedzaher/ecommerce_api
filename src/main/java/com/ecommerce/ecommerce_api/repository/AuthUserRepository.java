package com.ecommerce.ecommerce_api.repository;

import com.ecommerce.ecommerce_api.model.AuthUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends CrudRepository<AuthUser, Long> {

    Optional<AuthUser> findByUsername(String username);
}
