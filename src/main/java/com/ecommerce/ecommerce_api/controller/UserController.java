package com.ecommerce.ecommerce_api.controller;

import com.ecommerce.ecommerce_api.dto.CategoryDto;
import com.ecommerce.ecommerce_api.dto.UserDto;
import com.ecommerce.ecommerce_api.service.CategoryService;
import com.ecommerce.ecommerce_api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> findById(@PathVariable Long userId) {
        Optional<UserDto> user = userService.findById(userId);
        return user.isPresent() ?
                ResponseEntity.ok(user.get()) : ResponseEntity.notFound().build();
    }
}
