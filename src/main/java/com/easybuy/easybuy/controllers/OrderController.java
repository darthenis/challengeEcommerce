package com.easybuy.easybuy.controllers;


import com.easybuy.easybuy.DTO.OrderDTO;
import com.easybuy.easybuy.models.Order;
import com.easybuy.easybuy.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping("/orders")
    public Set<OrderDTO> getAll(){
        return orderService.findAll().stream().map(OrderDTO::new).collect(Collectors.toSet());
    }

}
