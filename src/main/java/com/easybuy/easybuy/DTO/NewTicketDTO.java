package com.easybuy.easybuy.DTO;

import com.easybuy.easybuy.models.Product;

import java.time.LocalDateTime;
import java.util.List;

public class NewTicketDTO {

private LocalDateTime dateTime;

private List<ApplyProductDTO> products;

    public NewTicketDTO(LocalDateTime dateTime, List<ApplyProductDTO> products) {
        this.dateTime = dateTime;
        this.products = products;
    }

    public List<ApplyProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ApplyProductDTO> products) {
        this.products = products;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
