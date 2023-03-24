package com.easybuy.easybuy.services.impl;

import com.easybuy.easybuy.models.Ticket;
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
    public void save(Ticket ticket) {
        orderRepository.save(ticket);
    }

    @Override
    public List<Ticket> findAll() {
        return orderRepository.findAll();
    }
}
