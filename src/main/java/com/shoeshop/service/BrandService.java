package com.shoeshop.service;

import com.shoeshop.dto.BrandDto;
import com.shoeshop.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {
    List<BrandDto> getAllBrandAndListProduct();

    BrandDto getBrandByIdAndProductPageOfProductLine(Long id, Pageable pageable);

    Boolean addBrands(Brand brand);

    List<Brand> findAll();

    Page<Brand> getBrandNews(Pageable pageable);
    Brand findLastBrand();

    Brand findBrandById(long id);

    Brand updateBrand(Brand c1);
}
