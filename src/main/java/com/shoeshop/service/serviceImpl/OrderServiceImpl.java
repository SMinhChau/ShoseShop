package com.shoeshop.service.serviceImpl;

import com.shoeshop.dao.impl.OrderDaoImpl;
import com.shoeshop.entity.Order;
import com.shoeshop.repository.OrderRepository;
import com.shoeshop.service.OrderService;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDaoImpl orderDaoImpl;
    
    @Override
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findOrdersByCustomerIdByDESC(Long id) {
        List<Order> list = orderRepository.findOrdersByCustomerId(id);
        Collections.reverse(list);
        return list;
    }

    @Override
    public Page<Order> findOrdersByStatusAndCreatedDateBetweenAndCustomerName(String name, String status, Date dateFrom, Date dateTo,
            Pageable pageable) {
        return orderRepository
                .findOrdersByStatusContainingAndCreatedDateIsGreaterThanEqualAndCreatedDateIsLessThanEqualAndCustomer_FullNameContainingIgnoreCase(
                        status, dateFrom, dateTo, name, pageable);
    }

    @Override
    public Page<Order> findOrdersByCustomerPhone(String phone, Pageable pageable) {
        return orderRepository.findOrdersByCustomer_Phone(phone, pageable);
    }

    @Override
    public Order getOrderById(long id) {
        return orderRepository.findOrderById(id);
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

	@Override
	public List<Order> findOrdersByCreatedDate(int year, int month, int day) {
		String date = "";
		if(month == -1 && day == -1) {
			date += year;
		}
		else if(day == -1) {
			String m = month >= 10 ? month+"": "0" + month;
			date = year + "-" + m;	
		}
		else if(year == -1) {
			String d = day >= 10 ? day+"": "0" + day;
			date = month + "-" + d;	
		}
		return orderDaoImpl.findOrdersByCreatedDate(date);
	}
}
