package com.shoeshop.dto;

import lombok.Data;

@Data
public class PromotionDto {
    private boolean status;
    private String message;
    private double discount;
    private String code;
}
