package com.shoeshop.dto;

import lombok.Data;


@Data
public class OrderAdminDto {
    private long id;
    private String code;
    private String createdDate;
    private String customerPhone;
    private String customerEmail;
    private String customerName;
    private String status;
    private double amount;
}
