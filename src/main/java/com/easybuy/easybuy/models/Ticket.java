package com.easybuy.easybuy.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String number;

    private String fullName;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    private LocalDateTime dateTime;

    @OneToMany(mappedBy = "ticket", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<TicketProduct> ticketProducts = new ArrayList<>();

    public Ticket(){}

    public Ticket(String number, String fullName, LocalDateTime dateTime) {
        this.number = number;
        this.fullName = fullName;
        this.dateTime = dateTime;
    }

    public void addTicketProduct(TicketProduct ticketProduct){

            ticketProduct.setTicket(this);
            this.ticketProducts.add(ticketProduct);

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<TicketProduct> getTicketProducts() {
        return ticketProducts;
    }

    public void setTicketProducts(List<TicketProduct> ticketProducts) {
        this.ticketProducts = ticketProducts;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
