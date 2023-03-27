package com.easybuy.easybuy.DTO;

import com.easybuy.easybuy.models.Client;
import com.easybuy.easybuy.models.Ticket;
import com.easybuy.easybuy.models.TicketProduct;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class TicketDTO {

    private Long id;

    private String number;

    private Double amount;

    private LocalDateTime dateTime;

    private Set<TicketProductDTO> ticketProducts;




    public TicketDTO(Ticket ticket) {
        this.id = ticket.getId();
        this.number = ticket.getNumber();
        this.amount = ticket.getAmount();
        this.dateTime = ticket.getDateTime();
        this.ticketProducts = ticket.getTicketProducts().stream().map(ticketProduct -> new TicketProductDTO(ticketProduct)).collect(Collectors.toSet());

    }

    public Long getId() {
        return id;
    }



    public String getNumber() {
        return number;
    }



    public Double getAmount() {
        return amount;
    }



    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Set<TicketProductDTO> getTicketProducts() {
        return ticketProducts;
    }
}
