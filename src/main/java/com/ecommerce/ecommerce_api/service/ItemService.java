package com.ecommerce.ecommerce_api.service;

import com.ecommerce.ecommerce_api.dto.ItemDto;
import com.ecommerce.ecommerce_api.dto.ItemFilterDto;
import com.ecommerce.ecommerce_api.dto.SearchItemDto;
import com.ecommerce.ecommerce_api.model.Item;
import com.ecommerce.ecommerce_api.repository.ItemRepository;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<ItemDto> findAll() {
        return ItemDto.of(this.itemRepository.findAll());
    }

    public List<SearchItemDto> searchItems(ItemFilterDto itemFilterDto) {
        List<Tuple> result = this.itemRepository.searchItems(itemFilterDto);
        return SearchItemDto.of(result);
    }

    public Optional<ItemDto> findById(Long id) {
        Item item = this.itemRepository.findById(id).orElse(null);
        return item == null ? Optional.empty() : Optional.of(ItemDto.of(item));
    }

    public Item save(Item item) {
        return this.itemRepository.save(item);
    }

}
