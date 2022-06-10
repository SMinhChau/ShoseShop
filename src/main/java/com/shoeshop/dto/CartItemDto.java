package com.shoeshop.dto;

import lombok.Data;

@Data
public class CartItemDto {
    private Long productSizeId;
    private String productName;
    private String productSize;
    private String avatar;
    private Double price;
    private int quantity;
    private int quantityMax;
}
