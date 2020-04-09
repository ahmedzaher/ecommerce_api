package com.ecommerce.ecommerce_api.service;

import com.ecommerce.ecommerce_api.dto.ItemFilterDto;
import com.ecommerce.ecommerce_api.dto.StoreFilterDto;
import com.ecommerce.ecommerce_api.dto.StoreItemDto;
import com.ecommerce.ecommerce_api.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    private ItemRepository itemRepository;

    public StoreService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<StoreItemDto> getStoreItems(StoreFilterDto storeFilter) {
        ItemFilterDto itemFilterDto = ItemFilterDto.builder()
                .name(storeFilter.getItemName())
                .maxPrice(storeFilter.getMaxPrice())
                .minPrice(storeFilter.getMinPrice())
                .available(true)
                .offset(storeFilter.getOffset())
                .pageSize(storeFilter.getPageSize())
                .build();
       return StoreItemDto.of(this.itemRepository.searchItems(itemFilterDto));
    }
}
