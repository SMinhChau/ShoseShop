package com.shoeshop.service;

import com.shoeshop.entity.ProductLine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductLineService {
    ProductLine getProductLine(Long id);

    List<ProductLine> getProductLinesByBrandId(long id);

    ProductLine addProductLine(ProductLine productLine);

    List<ProductLine> findAll();

    ProductLine updateProductLine(ProductLine productLine1);
}
