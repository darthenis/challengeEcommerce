package com.easybuy.easybuy.services.impl;

import com.easybuy.easybuy.DTO.ApplyProductDTO;
import com.easybuy.easybuy.DTO.NewTicketDTO;
import com.easybuy.easybuy.models.Ticket;
import com.easybuy.easybuy.repositories.TicketRepository;
import com.easybuy.easybuy.services.ProductService;
import com.easybuy.easybuy.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ProductService productService;

    @Override
    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket findByNumber(String number) {
        return ticketRepository.findByNumber(number);
    }


    @Override
    public Long findByMaxId() {
        return ticketRepository.findMaxId();
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return ticketRepository.findById(id);
    }

    @Override
    public Ticket createTicket(NewTicketDTO newTicketDTO) throws Exception {

        if(newTicketDTO.getDateTime() == null ) throw new Exception("missing date");

        if(newTicketDTO.getProducts() == null ) throw new Exception("missing products");

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

        Ticket newTicket = new Ticket(finalTicketNumber,newTicketDTO.getAmount(),newTicketDTO.getDateTime());
        ticketRepository.save(newTicket);




        double amount = 0.0;

        for(ApplyProductDTO applyProductDTO : newTicketDTO.getProducts()){

            double total = applyProductDTO.getPrice() * applyProductDTO.getQuantity();

            amount += total;

        }

        return new Ticket(finalTicketNumber, amount, newTicketDTO.getDateTime());

    }


}
