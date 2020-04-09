package com.ecommerce.ecommerce_api.controller;

import com.ecommerce.ecommerce_api.dto.StoreFilterDto;
import com.ecommerce.ecommerce_api.dto.StoreItemDto;
import com.ecommerce.ecommerce_api.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("store")
public class StoreController {

    private StoreService storeService;
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public ResponseEntity<List<StoreItemDto>> getStoreItems(StoreFilterDto filter) {
        return ResponseEntity.ok(this.storeService.getStoreItems(filter));
    }
}
