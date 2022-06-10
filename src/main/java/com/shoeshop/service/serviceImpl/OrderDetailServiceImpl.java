package com.shoeshop.service.serviceImpl;

import com.shoeshop.entity.OrderDetail;
import com.shoeshop.repository.OrderDetailRepository;
import com.shoeshop.service.OrderDetailService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public void addOrderDetail(OrderDetail orderDetail){
        orderDetailRepository.save(orderDetail);
    }

	@Override
	public List<OrderDetail> findOrdersDetailByOrderId(Long id) {
		return orderDetailRepository.findOrderDetailByOrderId(id);
	}

}
