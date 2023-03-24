package com.easybuy.easybuy.services;

import com.easybuy.easybuy.models.Order;

import java.util.List;

public interface OrderService {

    public void save(Order order);

    public List<Order> findAll();

}
