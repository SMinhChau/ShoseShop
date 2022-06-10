package com.shoeshop.service;

import com.shoeshop.entity.OrderDetail;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface OrderDetailService {
    void addOrderDetail(OrderDetail orderDetail);
	List<OrderDetail> findOrdersDetailByOrderId(Long id);
}
