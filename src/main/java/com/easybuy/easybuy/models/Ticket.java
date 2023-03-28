package com.easybuy.easybuy.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String number;

    private String fullName;

    private LocalDateTime dateTime;

    @OneToMany(mappedBy = "ticket", fetch = FetchType.EAGER)
    private List<TicketProduct> ticketProducts;

    public Ticket(){}

    public Ticket(String number, String fullName, List<TicketProduct> ticketProducts, LocalDateTime dateTime) {
        this.number = number;
        this.fullName = fullName;
        this.ticketProducts = ticketProducts;
        this.dateTime = dateTime;
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
}
