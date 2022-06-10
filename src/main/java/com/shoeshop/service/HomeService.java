package com.shoeshop.service;

import com.shoeshop.dto.BrandDto;
import com.shoeshop.entity.Brand;
import com.shoeshop.entity.Product;
import com.shoeshop.entity.SlideHome;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HomeService {
    List<SlideHome> getDataSlide();

}
