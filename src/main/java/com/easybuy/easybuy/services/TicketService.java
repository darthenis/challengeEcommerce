package com.easybuy.easybuy.services;

import com.easybuy.easybuy.DTO.CreateProductDTO;
import com.easybuy.easybuy.DTO.NewClientDTO;
import com.easybuy.easybuy.DTO.NewTicketDTO;
import com.easybuy.easybuy.models.Ticket;
import org.hibernate.criterion.Order;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TicketService {

    public void save(Ticket ticket);

    public List<Ticket> findAll();

    public Ticket finByNumber (String number);

    public Ticket createTicket(NewTicketDTO newTicketDTO) throws Exception;

    public Long findByMaxId();

}
