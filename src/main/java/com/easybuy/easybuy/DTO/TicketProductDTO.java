package com.easybuy.easybuy.DTO;

import com.easybuy.easybuy.models.TicketProduct;
import com.easybuy.easybuy.models.Product;

public class TicketProductDTO {

    private Long id;

    private Double price;

    private int quantity;

    private ProductDTO product;

    public TicketProductDTO(TicketProduct ticketProduct) {
        this.id = ticketProduct.getId();
        this.price = ticketProduct.getPrice();
        this.quantity = ticketProduct.getQuantity();
        this.product = new ProductDTO(ticketProduct.getProduct());
    }

    public Long getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }


    public ProductDTO getProduct() {
        return product;
    }


}
