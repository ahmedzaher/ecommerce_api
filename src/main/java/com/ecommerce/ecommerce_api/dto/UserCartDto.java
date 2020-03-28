package com.ecommerce.ecommerce_api.dto;

import com.ecommerce.ecommerce_api.model.Cart;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserCartDto {

    private Long id;
    private List<ItemDto> items;

    public static UserCartDto of(Cart cart) {
        return UserCartDto.builder()
                .id(cart.getId())
                .items(ItemDto.of(cart.getItems()))
                .build();
    }
}
