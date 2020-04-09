package com.ecommerce.ecommerce_api.dto;

import com.ecommerce.ecommerce_api.model.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private Long id;
    private String name;
    private Float price;
    private Integer availableQuantity;
    private boolean available;
    private LocalDateTime creationTime;
    private LocalDateTime lastUpdatedTime;
    private CategoryDto category;

    public static ItemDto of(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .availableQuantity(item.getAvailableQuantity())
                .available(item.isAvailable())
                .creationTime(item.getCreationTime())
                .lastUpdatedTime(item.getLastUpdatedTime())
                .category(CategoryDto.of(item.getCategory()))
                .build();
    }

    public static List<ItemDto> of(List<Item> items) {
        List<ItemDto> itemsDtos = new ArrayList<>();
        if (items != null) {
            items.forEach(item -> itemsDtos.add(of(item)));
        }
        return itemsDtos;
    }
}
