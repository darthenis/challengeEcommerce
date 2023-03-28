package com.easybuy.easybuy.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class PurchaseOrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private Double price;
    private int quantity;
    @ManyToOne(fetch = FetchType.EAGER)
    private PurchaseOrder purchaseOrder;

    @ManyToOne(fetch=FetchType.EAGER)
    private Product product;



    public PurchaseOrderProduct() {

    }

    public PurchaseOrderProduct(Double price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public PurchaseOrder getTicket() {
        return purchaseOrder;
    }

    public void setTicket(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

}
