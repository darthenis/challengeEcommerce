package com.easybuy.easybuy.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class NewTicketDTO {

private LocalDateTime dateTime;

private List<ApplyProductDTO> products;

private Double amount;

    public NewTicketDTO(LocalDateTime dateTime,Double amount, List<ApplyProductDTO> products) {
        this.dateTime = dateTime;
        this.products = products;
    }

    public Double getAmount() {
        return amount;
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
