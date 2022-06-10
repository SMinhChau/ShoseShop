package com.shoeshop.service;

import com.shoeshop.dto.CartDto;
import com.shoeshop.dto.CartItemDto;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface ShoppingCartService {

    void addCartIem(CartItemDto item);

    void removeCartItem(long productSizeId);

    void updateCartItem(Long productSizeId, int quantity);

    Collection<CartItemDto> getListCartItem();

    double getAmount();

    int getTotalQuantity();

    CartDto getCartDto();

    void clearCart();
}
