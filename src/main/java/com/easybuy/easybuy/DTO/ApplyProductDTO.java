package com.easybuy.easybuy.DTO;

public class ApplyProductDTO {


    private Long id;

    private Double price;

    private int quantity;

    public ApplyProductDTO(Long id, Double price, int quantity) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
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
}
