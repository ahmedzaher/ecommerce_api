package com.ecommerce.ecommerce_api.dto;

import com.ecommerce.ecommerce_api.model.Category;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private Long id;
    private String name;

    public static CategoryDto of(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static List<CategoryDto> of(List<Category> categories) {
        List<CategoryDto> categoriesDto = new ArrayList<>();
        if (categories != null) {
            categories.forEach(category -> categoriesDto.add(of(category)));
        }
        return categoriesDto;
    }
}
