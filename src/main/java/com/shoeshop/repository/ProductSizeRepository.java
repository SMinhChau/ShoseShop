package com.shoeshop.repository;

import com.shoeshop.entity.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, Long> {
    ProductSize findProductSizeById(Long id);

    List<ProductSize> findProductSizesByProductIdAndStatusAndQuantityGreaterThan(long id, boolean status, int quantity);

    List<ProductSize> findProductSizesByProductId(long id);

    ProductSize findProductSizeByProductIdAndSizeId(Long id, Long id2);
}
