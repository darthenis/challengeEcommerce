package com.easybuy.easybuy.DTO;

import com.easybuy.easybuy.models.Client;
import com.easybuy.easybuy.models.Ticket;
import com.easybuy.easybuy.models.TicketProduct;

import java.time.LocalDateTime;
import java.util.Set;

public class TicketDTO {

    private Long id;

    private String number;

    private Double amount;

    private LocalDateTime dateTime;

    private Set<TicketProduct> ticketProducts;

    private Client client;


    public TicketDTO(Ticket ticket) {
        this.id = ticket.getId();
        this.number = ticket.getNumber();
        this.amount = ticket.getAmount();
        this.dateTime = ticket.getDateTime();
        this.ticketProducts = ticket.getOrderProducts();
        this.client = ticket.getClient();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Set<TicketProduct> getOrderProducts() {
        return ticketProducts;
    }

    public void setOrderProducts(Set<TicketProduct> ticketProducts) {
        this.ticketProducts = ticketProducts;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
