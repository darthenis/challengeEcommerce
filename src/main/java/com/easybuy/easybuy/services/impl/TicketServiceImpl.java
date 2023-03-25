package com.easybuy.easybuy.services.impl;

import com.easybuy.easybuy.DTO.NewTicketDTO;
import com.easybuy.easybuy.models.Ticket;
import com.easybuy.easybuy.repositories.TicketRepository;
import com.easybuy.easybuy.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;




    @Override
    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket finByNumber(String number) {
        return ticketRepository.findByNumber(number);
    }


    @Override
    public Long findByMaxId() {
        return ticketRepository.findMaxId();
    }

    @Override
    public  void createTicket(NewTicketDTO newTicketDTO) throws Exception {

        if(newTicketDTO.getAmount() == null ) throw new Exception("missing amount");

        if(newTicketDTO.getDateTime() == null ) throw new Exception("missing date");


        int serialNumber = 1;
        Long maxId = ticketRepository.findMaxId();
        String serial;
        String ticketNumber;
        String finalTicketNumber;

        if (maxId == null){
            maxId =  1l;
        }else {
            maxId += 1l ;
        }
        System.out.println(maxId);
        while(maxId > 99999){
            maxId -= 99999;
            serialNumber++;
        }

        serial = String.format("%03d", serialNumber);
        ticketNumber = String.format("%06d", maxId);
        System.out.println(ticketNumber);
        finalTicketNumber = serial + "-" +ticketNumber;
        System.out.println(finalTicketNumber);
        Ticket newTicket = new Ticket(finalTicketNumber,newTicketDTO.getAmount(),newTicketDTO.getDateTime());
        ticketRepository.save(newTicket);
        System.out.println(newTicket.getNumber());

    }





}
