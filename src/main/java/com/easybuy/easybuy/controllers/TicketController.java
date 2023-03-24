package com.easybuy.easybuy.controllers;


import com.easybuy.easybuy.DTO.TicketDTO;
import com.easybuy.easybuy.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TicketController {
    @Autowired
    TicketService orderService;

    @RequestMapping("/orders")
    public Set<TicketDTO> getAll(){
        return orderService.findAll().stream().map(TicketDTO::new).collect(Collectors.toSet());
    }

}
