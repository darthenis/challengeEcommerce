package com.easybuy.easybuy.services;

import com.easybuy.easybuy.models.TicketProduct;

import java.util.List;

public interface TicketProductService {
    void save (TicketProduct ticketProduct);

    List<TicketProduct> findAll() ;


}
