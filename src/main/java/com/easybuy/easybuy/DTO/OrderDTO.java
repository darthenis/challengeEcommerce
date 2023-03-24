package com.easybuy.easybuy.DTO;

import com.easybuy.easybuy.models.Client;
import com.easybuy.easybuy.models.Order;
import com.easybuy.easybuy.models.OrderProduct;

import java.time.LocalDateTime;
import java.util.Set;

public class OrderDTO {

    private Long id;

    private String number;

    private Double amount;

    private LocalDateTime dateTime;

    private Set<OrderProduct> orderProducts;

    private Client client;


    public OrderDTO(Order order) {
        this.id = order.getId();
        this.number = order.getNumber();
        this.amount = order.getAmount();
        this.dateTime = order.getDateTime();
        this.orderProducts = order.getOrderProducts();
        this.client = order.getClient();
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

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
