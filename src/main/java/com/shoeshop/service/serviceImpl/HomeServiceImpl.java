package com.shoeshop.service.serviceImpl;

import com.shoeshop.entity.*;
import com.shoeshop.repository.ProductRepository;
import com.shoeshop.repository.SlideHomeRepository;
import com.shoeshop.repository.TagRepository;
import com.shoeshop.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private SlideHomeRepository slideHomeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<SlideHome> getDataSlide() {
        return slideHomeRepository.findAll();
    }

}
