package com.shoeshop.dto;

import com.shoeshop.entity.Category;
import com.shoeshop.entity.Product;
import com.shoeshop.entity.ProductLine;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private Double price;
    private Double promotionPrice;
    private String avatar;
    private ProductLineDto productLineDto;
    private Category category;
    private Boolean status;
}
