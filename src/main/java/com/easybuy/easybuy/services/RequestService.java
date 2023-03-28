package com.easybuy.easybuy.services;

import com.easybuy.easybuy.DTO.NewTicketDTO;
import com.easybuy.easybuy.models.PurchaseOrder;

import java.util.List;
import java.util.Optional;

public interface RequestService {

    public void save(PurchaseOrder purchaseOrder);

    public List<PurchaseOrder> findAll();

    public PurchaseOrder findByNumber (String number);

    public PurchaseOrder createTicket(NewTicketDTO newTicketDTO) throws Exception;

    public Long findByMaxId();

    public Optional<PurchaseOrder> findById (Long id);

}
