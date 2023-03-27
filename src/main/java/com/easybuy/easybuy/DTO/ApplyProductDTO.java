package com.easybuy.easybuy.DTO;

public class ApplyProductDTO {


    private Long idProduct;

    private Double price;

    private int quantity;


    public ApplyProductDTO(Long id, Double price, int quantity) {
        this.idProduct = id;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return idProduct;
    }

    public void setId(Long id) {
        this.idProduct = id;
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
