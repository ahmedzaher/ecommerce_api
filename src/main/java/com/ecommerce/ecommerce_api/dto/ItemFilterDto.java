package com.ecommerce.ecommerce_api.dto;

import lombok.Data;

@Data
public class ItemFilterDto {

    private String name;
    private Double minPrice;
    private Double maxPrice;
    private int pageSize = 25;
    private int offset = 0;
}
