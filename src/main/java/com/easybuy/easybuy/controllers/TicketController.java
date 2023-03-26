package com.easybuy.easybuy.controllers;


import com.easybuy.easybuy.DTO.NewTicketDTO;
import com.easybuy.easybuy.DTO.TicketDTO;
import com.easybuy.easybuy.models.Client;
import com.easybuy.easybuy.models.Ticket;
import com.easybuy.easybuy.services.ClientService;
import com.easybuy.easybuy.services.ProductService;
import com.easybuy.easybuy.services.TicketProductService;
import com.easybuy.easybuy.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @Autowired
    ProductService productService;

    @Autowired
    TicketProductService ticketProductService;

    @Autowired
    ClientService clientService;


    @RequestMapping("/orders")
    public Set<TicketDTO> getAll(){
        return ticketService.findAll().stream().map(TicketDTO::new).collect(Collectors.toSet());
    }

    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping("/client/current/ticket")
    public ResponseEntity<Object> newTicket(@RequestBody NewTicketDTO newTicketDTO, Authentication authentication) throws Exception {

        if(!productService.productsExists(newTicketDTO.getProducts())) return new ResponseEntity<>("product not found", HttpStatus.FORBIDDEN);

        Ticket ticket = ticketService.createTicket(newTicketDTO);

        ticketProductService.createTicketProduct(newTicketDTO.getProducts(), ticket);

        Optional<Client> client = clientService.findByEmail(authentication.getName());

        client.get().addTicket(ticket);

        clientService.save(client.get());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
