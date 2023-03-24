package com.easybuy.easybuy.services;

import com.easybuy.easybuy.DTO.CreateProductDTO;
import com.easybuy.easybuy.DTO.NewClientDTO;
import com.easybuy.easybuy.models.Ticket;

import java.util.List;

public interface TicketService {

    public void save(Ticket ticket);

    public List<Ticket> findAll();



}
