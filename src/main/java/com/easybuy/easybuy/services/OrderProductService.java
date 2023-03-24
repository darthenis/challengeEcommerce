package com.easybuy.easybuy.services;

import com.easybuy.easybuy.models.TicketProduct;

import java.util.List;

public interface OrderProductService {
    void save (TicketProduct ticketProduct);

    List<TicketProduct> findAll() ;


}
