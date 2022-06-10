package com.shoeshop.repository;

import com.shoeshop.entity.ProductLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductLineRepository extends JpaRepository<ProductLine,Long> {
    ProductLine findProductLineById(Long id);
    
    List<ProductLine> findProductLinesByBrandId(long id);

}
