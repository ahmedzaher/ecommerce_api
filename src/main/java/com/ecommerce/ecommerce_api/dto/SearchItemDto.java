package com.ecommerce.ecommerce_api.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class SearchItemDto {

    private Long id;
    private String name;
    private Float price;

    public static SearchItemDto of(Tuple record) {
        return SearchItemDto.builder()
                .id((Long) record.get(0))
                .name((String) record.get(1))
                .price((Float) record.get(2))
                .build();
    }

    public static List<SearchItemDto> of(List<Tuple> records) {
        List<SearchItemDto> items = new ArrayList<>();
        records.forEach( record -> items.add(of(record)));
        return items;
    }

}
