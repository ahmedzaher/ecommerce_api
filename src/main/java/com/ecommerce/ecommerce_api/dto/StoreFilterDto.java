package com.ecommerce.ecommerce_api.dto;

import lombok.Data;

@Data
public class StoreFilterDto {

    private int pageSize = 25;
    private int offset;
    private String itemName;
    private Float minPrice;
    private Float maxPrice;
}
