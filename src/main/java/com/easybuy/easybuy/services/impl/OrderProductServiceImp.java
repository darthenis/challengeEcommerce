package com.easybuy.easybuy.services.impl;

import com.easybuy.easybuy.models.OrderProduct;
import com.easybuy.easybuy.repositories.OrderProductRepository;
import com.easybuy.easybuy.services.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderProductServiceImp implements OrderProductService{

    @Autowired
    private OrderProductRepository orderProductRepository;


    @Override
    public void save(OrderProduct orderProduct) {

    }

    @Override
    public List<OrderProduct> findAll() {
        return orderProductRepository.findAll();
    }


}
