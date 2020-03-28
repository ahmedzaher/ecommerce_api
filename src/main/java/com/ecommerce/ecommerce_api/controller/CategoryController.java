package com.ecommerce.ecommerce_api.controller;

import com.ecommerce.ecommerce_api.dto.CategoryDto;
import com.ecommerce.ecommerce_api.model.Category;
import com.ecommerce.ecommerce_api.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> findById(@PathVariable Long categoryId) {
        Optional<CategoryDto> category = categoryService.findById(categoryId);
        return  category.isPresent() ?
                ResponseEntity.ok(category.get()) : ResponseEntity.notFound().build();
    }
}
