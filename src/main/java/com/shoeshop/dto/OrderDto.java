package com.shoeshop.dto;

import lombok.Data;

@Data
public class OrderDto {
    private String customerPhone;
    private long shipAddressId;
    private String promotionCode;
    private String note;
}
