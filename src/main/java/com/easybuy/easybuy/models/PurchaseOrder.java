package com.easybuy.easybuy.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String number;

    private Double amount;

    private LocalDateTime DateTime;

    private boolean state = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy= "purchaseOrder", fetch=FetchType.EAGER)
    private Set<PurchaseOrderProduct> purchaseOrderProducts = new HashSet<>();

    public PurchaseOrder(){}

    public PurchaseOrder(String number, Double amount, LocalDateTime dateTime) {
        this.number = number;
        this.amount = amount;
        DateTime = dateTime;
    }

    public void addTicketProduct(PurchaseOrderProduct purchaseOrderProduct){

        purchaseOrderProduct.setTicket(this);

        this.purchaseOrderProducts.add(purchaseOrderProduct);

    }

    public Set<PurchaseOrderProduct> getTicketProducts() {
        return purchaseOrderProducts;
    }

    public void setTicketProducts(Set<PurchaseOrderProduct> purchaseOrderProducts) {
        this.purchaseOrderProducts = purchaseOrderProducts;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
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
