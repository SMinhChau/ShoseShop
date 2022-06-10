package com.shoeshop.repository;

import com.shoeshop.entity.Order;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrdersByCustomerId(Long id);

    Order findOrderById(long id);

    Page<Order> findOrdersByStatusContainingAndCreatedDateIsGreaterThanEqualAndCreatedDateIsLessThanEqualAndCustomer_FullNameContainingIgnoreCase(
            String status, Date fromDate, Date toDate, String name, Pageable pageable);

    Page<Order> findOrdersByCustomer_Phone(String phone, Pageable pageable);

}
