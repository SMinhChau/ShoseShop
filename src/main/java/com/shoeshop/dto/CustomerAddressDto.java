package com.shoeshop.dto;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CustomerAddressDto {
    private long addressId;
    private long customerId;
    private String detail;
}
