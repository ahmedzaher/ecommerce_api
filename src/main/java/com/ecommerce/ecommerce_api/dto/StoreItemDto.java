package com.ecommerce.ecommerce_api.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class StoreItemDto {

    private Long id;
    private String name;
    private Float price;

    public static StoreItemDto of(Tuple record) {
        return StoreItemDto.builder()
                .id((Long) record.get(0))
                .name((String) record.get(1))
                .price((Float) record.get(2))
                .build();
    }

    public static List<StoreItemDto> of(List<Tuple> records) {
        List<StoreItemDto> items = new ArrayList<>();
        records.forEach( record -> items.add(of(record)));
        return items;
    }

}
