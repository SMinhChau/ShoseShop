package com.shoeshop.dto;

import com.shoeshop.entity.Product;
import com.shoeshop.entity.ProductLine;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Data
public class BrandDto {
    private Long id;
    private String name;
    private String image;
    private List<ProductLineDto> productLines = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
}
