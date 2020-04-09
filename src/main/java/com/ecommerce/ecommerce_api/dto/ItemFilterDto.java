package com.ecommerce.ecommerce_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ItemFilterDto {

    private String name;
    private Float minPrice;
    private Float maxPrice;
    private Boolean available;
    @Builder.Default
    private int pageSize = 25;
    @Builder.Default
    private int offset = 0;
}
