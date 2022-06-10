package com.shoeshop.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shoeshop.dao.OrderDao;
import com.shoeshop.entity.Order;

@Repository
public class OrderDaoImpl implements OrderDao {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	@Transactional
	public List<Order> findOrdersByCreatedDate(String date) {
//		List<Order> list = entityManager.createNativeQuery("select * from orders where created_date like '%"+date+"%'").getResultList();
		 List<Order> list = entityManager.createNativeQuery("select * from orders where created_date like '%"+date+"%'", Order.class).getResultList();
		return list;
	}

}
