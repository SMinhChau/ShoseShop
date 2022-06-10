package com.shoeshop.dto;

import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class CartDto {
    private Collection<CartItemDto> items;
    private double totalAmount;
    private int totalQuantity;
}
