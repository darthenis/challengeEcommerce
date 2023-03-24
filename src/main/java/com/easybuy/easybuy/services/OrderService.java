package com.easybuy.easybuy.services;

import com.easybuy.easybuy.models.Ticket;

import java.util.List;

public interface OrderService {

    public void save(Ticket ticket);

    public List<Ticket> findAll();

}
