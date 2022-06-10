package com.shoeshop.service;

import com.shoeshop.entity.Order;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface OrderService {
    Order addOrder(Order order);

	List<Order> findOrdersByCustomerIdByDESC(Long id);
    Page<Order> findOrdersByStatusAndCreatedDateBetweenAndCustomerName(String name, String status, Date dateFrom, Date dateTo, Pageable pageable);

    Page<Order> findOrdersByCustomerPhone(String phone, Pageable pageable);

    Order getOrderById(long id);

    void updateOrder(Order order);

	List<Order> findOrdersByCreatedDate(int year, int month, int day);
}
