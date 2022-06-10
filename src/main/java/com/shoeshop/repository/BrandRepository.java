package com.shoeshop.repository;

import com.shoeshop.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> {
    @Override
    List<Brand> findAll();

    Brand findBrandById(Long id);

    Brand findTopByOrderByIdDesc();

}
