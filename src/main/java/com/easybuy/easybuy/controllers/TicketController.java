package com.easybuy.easybuy.controllers;

import com.easybuy.easybuy.DTO.TicketDTO;
import com.easybuy.easybuy.models.Ticket;
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
    TicketService ticketService;

    @RequestMapping("/tickets")
    public Set<TicketDTO> allTickets(){
        return ticketService.findAll().stream().map(ticket ->new TicketDTO(ticket)).collect(Collectors.toSet());
    }

}
