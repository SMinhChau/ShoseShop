package com.shoeshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shoeshop.entity.ProductSize;

@Service
public interface ProductSizeService {
    ProductSize getProductSizeById(long id);
	boolean addProductSize(ProductSize productSize);
    void updateProductSize(ProductSize productSize);
    List<ProductSize> getProductSizeByProductId(long id);
	ProductSize getProductSizeByProductIdAndSizeId(Long id, Long id2);
}
