package com.easybuy.easybuy.services.impl;

import com.easybuy.easybuy.models.TicketProduct;
import com.easybuy.easybuy.repositories.OrderProductRepository;
import com.easybuy.easybuy.services.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductServiceImp implements OrderProductService{

    @Autowired
    private OrderProductRepository orderProductRepository;


    @Override
    public void save(TicketProduct ticketProduct) {

    }

    @Override
    public List<TicketProduct> findAll() {
        return orderProductRepository.findAll();
    }


}
