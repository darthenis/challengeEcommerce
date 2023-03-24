package com.easybuy.easybuy.services.impl;

import com.easybuy.easybuy.DTO.CreateProductDTO;
import com.easybuy.easybuy.models.Client;
import com.easybuy.easybuy.models.Product;
import com.easybuy.easybuy.models.Ticket;
import com.easybuy.easybuy.repositories.TicketRepository;
import com.easybuy.easybuy.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }




}
