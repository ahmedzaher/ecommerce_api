package com.ecommerce.ecommerce_api.repository;

import com.ecommerce.ecommerce_api.dto.ItemFilterDto;

import javax.persistence.Tuple;
import java.util.List;

public interface ItemRepositoryCustom {
    List<Tuple> searchItems(ItemFilterDto searchDto);
}
