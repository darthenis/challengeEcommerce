package com.easybuy.easybuy.services;

import com.easybuy.easybuy.models.Client;
import com.easybuy.easybuy.models.PurchaseOrder;
import com.easybuy.easybuy.models.Ticket;

import java.util.Optional;
import java.util.Set;

public interface TicketService {

    public Ticket createTicket(PurchaseOrder purchaseOrder, Client client) throws Exception;

    public Optional<Ticket> findByid(Long id);

    public Set<Ticket> findAll();

    public void save(Ticket ticket);

}
