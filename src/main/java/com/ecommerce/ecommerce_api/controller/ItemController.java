package com.ecommerce.ecommerce_api.controller;

import com.ecommerce.ecommerce_api.dto.ItemDto;
import com.ecommerce.ecommerce_api.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("item")
public class ItemController {
    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<ItemDto>> findAll() {
        return ResponseEntity.ok(itemService.findAll());
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemDto> findById(@PathVariable Long itemId) {
        Optional<ItemDto> Item = itemService.findById(itemId);
        return Item.isPresent() ?
                ResponseEntity.ok(Item.get()) : ResponseEntity.notFound().build();
    }
}
