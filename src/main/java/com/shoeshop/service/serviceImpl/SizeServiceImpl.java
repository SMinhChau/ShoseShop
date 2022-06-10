package com.shoeshop.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoeshop.entity.Size;
import com.shoeshop.repository.SizeReponsitory;
import com.shoeshop.service.SizeService;

@Service
public class SizeServiceImpl implements SizeService {

	@Autowired
	private SizeReponsitory sizeReponsitory;

	@Override
	public List<Size> findSizeAll() {
		return sizeReponsitory.findAll();
	}

	@Override
	public Size findById(Integer integer) {
		// TODO Auto-generated method stub
		return sizeReponsitory.findSizeById(integer.longValue());
	}
}
