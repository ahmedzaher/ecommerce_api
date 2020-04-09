package com.ecommerce.ecommerce_api.dto;

import com.ecommerce.ecommerce_api.model.Item;
import lombok.Builder;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class CartItemDto {

    private Long itemId;
    private String name;
    private String category;

    public static List<CartItemDto> of(List<Item> items) {
        List<CartItemDto> itemDtos = new ArrayList<>();
        items.forEach(item -> itemDtos.add(of(item)));
        return itemDtos;
    }

    public static CartItemDto of(Item item) {
        return CartItemDto.builder()
                .itemId(item.getId())
                .name(item.getName())
                .category(item.getCategory().getName())
                .build();
    }
}
