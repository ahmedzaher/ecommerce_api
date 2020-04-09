package com.ecommerce.ecommerce_api.service;

import com.ecommerce.ecommerce_api.dto.UserCartDto;
import com.ecommerce.ecommerce_api.model.Cart;
import com.ecommerce.ecommerce_api.model.Item;
import com.ecommerce.ecommerce_api.model.User;
import com.ecommerce.ecommerce_api.repository.CartRepository;
import com.ecommerce.ecommerce_api.repository.ItemRepository;
import com.ecommerce.ecommerce_api.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@Slf4j
public class CartService {
    CartRepository cartRepository;
    UserRepository userRepository;
    ItemRepository itemRepository;

    public CartService(CartRepository cartRepository, UserRepository userRepository, ItemRepository itemRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    public Optional<UserCartDto> getUserCart(Long userId) {
        Optional<Cart> userCart = cartRepository.getUserCart(userId);
        return userCart.isPresent() ?
                Optional.of(UserCartDto.of(userCart.get())) : Optional.empty();

    }
    public Cart addItem(Long userId, Long itemId) {
        Cart userCart = cartRepository.getUserCart(userId).orElseGet(() -> createNewCart(userId));
        Item item = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);
        userCart.getItems().add(item);
        return cartRepository.save(userCart);
    }

    public boolean removeItem(Long cartId, Long itemId) {
        Cart userCart = cartRepository.findById(cartId).orElseThrow(EntityNotFoundException::new);
        Item item = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);
        if (userCart.getItems().remove(item)) {
            cartRepository.save(userCart);
            return true;
        }
        return false;
    }

    public Cart createNewCart(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        return cartRepository.save(new Cart(user));
    }
}
