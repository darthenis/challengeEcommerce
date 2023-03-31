package com.easybuy.easybuy.services.impl;

import com.easybuy.easybuy.DTO.ApplyProductDTO;
import com.easybuy.easybuy.models.Client;
import com.easybuy.easybuy.models.PurchaseOrder;
import com.easybuy.easybuy.models.Ticket;
import com.easybuy.easybuy.models.TicketProduct;
import com.easybuy.easybuy.repositories.TicketRepository;
import com.easybuy.easybuy.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TicketImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public Ticket createTicket(PurchaseOrder purchaseOrder, Client client) throws Exception{

        if(purchaseOrder.getDateTime() == null ) throw new Exception("missing date");

        if(purchaseOrder.getTicketProducts() == null ) throw new Exception("missing products");

        int serialNumber = 1;
        Long maxId = ticketRepository.findMaxId();
        String serial;
        String ticketNumber;
        String finalTicketNumber;

        if (maxId == null){
            maxId = 1L;
        }else {
            maxId += 1L;
        }

        while(maxId > 99999){
            maxId -= 99999;
            serialNumber++;
        }

        serial = String.format("%03d", serialNumber);
        ticketNumber = String.format("%06d", maxId);
        finalTicketNumber = serial + "-" +ticketNumber;

        List<TicketProduct> ticketProducts = purchaseOrder.getTicketProducts().stream().map(TicketProduct::new).collect(Collectors.toList());

        Ticket newTicket = new Ticket(finalTicketNumber, purchaseOrder.getClient().getName() + " " + purchaseOrder.getClient().getLastName(), purchaseOrder.getDateTime());

        double amount = 0.0;

        for(TicketProduct ticketProduct : ticketProducts){

            double total = ticketProduct.getPrice() * ticketProduct.getQuantity();

            amount += total;

            newTicket.addTicketProduct(ticketProduct);

        }

        newTicket.setClient(client);

        ticketRepository.save(newTicket);

        return newTicket;

    }

    @Override
    public Optional<Ticket> findByid(Long id) {
        return ticketRepository.findById(id);
    }

    @Override
    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }


}
