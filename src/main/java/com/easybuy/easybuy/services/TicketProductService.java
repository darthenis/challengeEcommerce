package com.easybuy.easybuy.services;

import com.easybuy.easybuy.DTO.ApplyProductDTO;
import com.easybuy.easybuy.models.Ticket;
import com.easybuy.easybuy.models.TicketProduct;

import java.util.List;

public interface TicketProductService {
    void save (TicketProduct ticketProduct);

    List<TicketProduct> findAll() ;

    void createTicketProduct(List<ApplyProductDTO> products, Ticket ticket);
}
