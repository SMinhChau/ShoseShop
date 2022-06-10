package com.shoeshop.dto;

import com.shoeshop.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductLineDto {

    private Long id;
    private String name;
    private Page<Product> productPage;

    public ProductLineDto(Long id) {
        this.id = id;
    }
}
