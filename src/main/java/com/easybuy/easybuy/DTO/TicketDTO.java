package com.easybuy.easybuy.DTO;

import com.easybuy.easybuy.models.Client;
import com.easybuy.easybuy.models.Ticket;
import com.easybuy.easybuy.models.TicketProduct;
import java.time.LocalDateTime;
import java.util.List;

public class TicketDTO {

    private Long id;
    private String number;
    private String fullName;
    private Client client;
    private LocalDateTime date;
    private List<TicketProduct> ticketProducts;

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getFullName() {
        return fullName;
    }

    public Client getClient() {
        return client;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public List<TicketProduct> getTicketProducts() {
        return ticketProducts;
    }

    public TicketDTO(Ticket ticket) {
        this.id = ticket.getId();
        this.number = ticket.getNumber();
        this.fullName = ticket.getFullName();
        this.client = ticket.getClient();
        this.date = ticket.getDateTime();
        this.ticketProducts = ticket.getTicketProducts();



    }
}
