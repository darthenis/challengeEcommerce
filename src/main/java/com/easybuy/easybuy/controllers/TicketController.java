package com.easybuy.easybuy.controllers;


import com.easybuy.easybuy.DTO.NewTicketDTO;
import com.easybuy.easybuy.DTO.TicketDTO;
import com.easybuy.easybuy.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @RequestMapping("/orders")
    public Set<TicketDTO> getAll(){
        return ticketService.findAll().stream().map(TicketDTO::new).collect(Collectors.toSet());
    }

    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping("/orders")
    public ResponseEntity<Object> newTicket(@RequestBody NewTicketDTO newTicketDTO) throws Exception {
        ticketService.createTicket(newTicketDTO) ;
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
