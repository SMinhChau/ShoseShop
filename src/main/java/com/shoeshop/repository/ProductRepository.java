package com.shoeshop.repository;

import com.shoeshop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    @Query(nativeQuery = true,value = "select top(?2) p.id,p.created_by,p.created_date,p.modified_by,p.modified_date,p.name,p.price,p.promotion_price,p.avatar,p.category_id,p.product_line_id, p.status from ((product p\n" +
            "inner join product_line pl\n" +
            "on p.product_line_id = pl.id)\n" +
            "inner join brand b on pl.brand_id = b.id)\n" +
            "where b.id = ?1\n" +
            "ORDER BY RAND()")
    List<Product> findProductsByBrandRandomLimit(long brandI, int limit);

    Product findProductById(Long id);

    List<Product> findTop20ByProductLineIdOrderByName(Long id);

    Page<Product> findProductsByProductLineId(Long id, Pageable pageable);

    Page<Product> findAll(Pageable pageable);

    Product findTopByOrderByIdDesc();

    @Query(nativeQuery = true, value = "select * from product where name like %?1%")
    Page<Product> findProductsByNameContainingIgnoreCaseCustom(String name, Pageable pageable);

    Page<Product> findProductsByNameContainingIgnoreCase(String name, Pageable pageable);
    
}
