package com.ecommerce.ecommerce_api.service;

import com.ecommerce.ecommerce_api.dto.CategoryDto;
import com.ecommerce.ecommerce_api.model.Category;
import com.ecommerce.ecommerce_api.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDto> findAll(){
        return CategoryDto.of(this.categoryRepository.findAll());
    }

    public Optional<CategoryDto> findById(Long id) {
        Optional<Category> category = this.categoryRepository.findById(id);
        if(category.isPresent()) {
            return Optional.of(CategoryDto.of(category.get()));
        } else {
            return Optional.empty();
        }
    }
    public Category save(Category category) {
        return this.categoryRepository.save(category);
    }
}
