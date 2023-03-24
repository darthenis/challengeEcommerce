package com.easybuy.easybuy.services.impl;

import com.easybuy.easybuy.models.Order;
import com.easybuy.easybuy.repositories.OrderRepository;
import com.easybuy.easybuy.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
