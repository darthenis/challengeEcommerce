package com.easybuy.easybuy.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class TicketProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String name;

    private Double price;

    private Long productId;

    private int quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    private Ticket ticket;

    public TicketProduct(){}

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public TicketProduct(String name, Double price, int quantity, Long productId) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.productId = productId;
    }

    public TicketProduct(PurchaseOrderProduct purchaseOrderProduct) {
        this.name = purchaseOrderProduct.getProduct().getName();
        this.price = purchaseOrderProduct.getPrice();
        this.quantity = purchaseOrderProduct.getQuantity();
        this.productId = purchaseOrderProduct.getProduct().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
