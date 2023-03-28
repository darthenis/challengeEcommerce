package com.easybuy.easybuy.services;

import com.easybuy.easybuy.models.PurchaseOrder;
import com.easybuy.easybuy.models.Ticket;

import java.util.Optional;

public interface TicketService {

    public Ticket createTicket(PurchaseOrder purchaseOrder) throws Exception;

    public Optional<Ticket> findByid(Long id);

}
