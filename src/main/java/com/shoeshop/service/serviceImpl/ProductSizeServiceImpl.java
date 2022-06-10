package com.shoeshop.service.serviceImpl;

import com.shoeshop.entity.ProductSize;
import com.shoeshop.repository.ProductSizeRepository;
import com.shoeshop.service.ProductSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSizeServiceImpl implements ProductSizeService {

    @Autowired
    private ProductSizeRepository productSizeRepository;

    @Override
    public ProductSize getProductSizeById(long id) {
        return productSizeRepository.findProductSizeById(id);
    }

    @Override
    public boolean addProductSize(ProductSize productSize) {
        return productSizeRepository.save(productSize) != null;
    }

    @Override
    public void updateProductSize(ProductSize productSize) {
        productSizeRepository.save(productSize);
    }

    @Override
    public List<ProductSize> getProductSizeByProductId(long id) {
        return productSizeRepository.findProductSizesByProductId(id);
    }

    @Override
    public ProductSize getProductSizeByProductIdAndSizeId(Long id, Long id2) {
        return productSizeRepository.findProductSizeByProductIdAndSizeId(id, id2);
    }
}
