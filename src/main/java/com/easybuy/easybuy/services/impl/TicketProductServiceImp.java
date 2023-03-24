package com.easybuy.easybuy.services.impl;

import com.easybuy.easybuy.models.TicketProduct;
import com.easybuy.easybuy.repositories.TicketProductRepository;
import com.easybuy.easybuy.services.TicketProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketProductServiceImp implements TicketProductService {

    @Autowired
    private TicketProductRepository orderProductRepository;


    @Override
    public void save(TicketProduct ticketProduct) {

    }

    @Override
    public List<TicketProduct> findAll() {
        return orderProductRepository.findAll();
    }


}
