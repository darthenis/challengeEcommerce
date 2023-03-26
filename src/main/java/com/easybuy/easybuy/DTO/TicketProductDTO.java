package com.easybuy.easybuy.DTO;

import com.easybuy.easybuy.models.TicketProduct;
import com.easybuy.easybuy.models.Product;

public class TicketProductDTO {

    private Long id;

    private Double price;

    private int quantity;

    private Product product;

    public TicketProductDTO(TicketProduct ticketProduct) {
        this.id = ticketProduct.getId();
        this.price = ticketProduct.getPrice();
        this.quantity = ticketProduct.getQuantity();
        this.product = ticketProduct.getProduct();
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
