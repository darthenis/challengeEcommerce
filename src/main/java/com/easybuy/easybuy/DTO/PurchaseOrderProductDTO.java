package com.easybuy.easybuy.DTO;

import com.easybuy.easybuy.models.PurchaseOrderProduct;

public class PurchaseOrderProductDTO {

    private Long id;

    private Double price;

    private int quantity;

    private ProductDTO product;

    public PurchaseOrderProductDTO(PurchaseOrderProduct purchaseOrderProduct) {
        this.id = purchaseOrderProduct.getId();
        this.price = purchaseOrderProduct.getPrice();
        this.quantity = purchaseOrderProduct.getQuantity();
        this.product = new ProductDTO(purchaseOrderProduct.getProduct());
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
