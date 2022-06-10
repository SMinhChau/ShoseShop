package com.shoeshop.dao;

import java.util.Date;
import java.util.List;

import com.shoeshop.entity.Order;

public interface OrderDao {
	List<Order> findOrdersByCreatedDate(String date);
}
