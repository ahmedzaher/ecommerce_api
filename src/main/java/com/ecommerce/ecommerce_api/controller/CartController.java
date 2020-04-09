package com.ecommerce.ecommerce_api.controller;

import com.ecommerce.ecommerce_api.configuration.AuthUserDetails;
import com.ecommerce.ecommerce_api.dto.CartItemDto;
import com.ecommerce.ecommerce_api.dto.UserCartDto;
import com.ecommerce.ecommerce_api.model.User;
import com.ecommerce.ecommerce_api.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping("cart")
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<UserCartDto> getUserCart() {
        User user = ((AuthUserDetails)SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal()).getSystemUser();
        Optional<UserCartDto> cart = cartService.getUserCart(user.getId());
        return cart.isPresent() ? ResponseEntity.ok(cart.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping("item")
    public ResponseEntity<UserCartDto> addCartItem(@RequestBody CartItemDto cartItem) {
        User user = ((AuthUserDetails)SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal()).getSystemUser();
        UserCartDto cart = UserCartDto.of(cartService.addItem(user.getId(), cartItem.getItemId()));
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("{cartId}/item/{itemId}")
    public ResponseEntity<UserCartDto> deleteCartItem(@PathVariable Long cartId, @PathVariable Long itemId) {
        boolean deleted;
        try {
            deleted = cartService.removeItem(cartId, itemId);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
