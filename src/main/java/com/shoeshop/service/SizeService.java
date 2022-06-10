package com.shoeshop.service;

import com.shoeshop.entity.Size;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface SizeService {
    List<Size> findSizeAll();
	Size findById(Integer integer);
}
