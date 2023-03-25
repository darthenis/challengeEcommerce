package com.easybuy.easybuy.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String number;

    private Double amount;

    private LocalDateTime DateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy= "ticket", fetch=FetchType.EAGER)
    private Set<TicketProduct> ticketProducts = new HashSet<>();



    public Ticket(){}

    public Ticket(String number, Double amount, LocalDateTime dateTime) {
        this.number = number;
        this.amount = amount;
        DateTime = dateTime;
    }


    public Set<TicketProduct> getTicketProducts() {
        return ticketProducts;
    }

    public void setTicketProducts(Set<TicketProduct> ticketProducts) {
        this.ticketProducts = ticketProducts;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
        return DateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        DateTime = dateTime;
    }
}
