package com.easybuy.easybuy.DTO;

import com.easybuy.easybuy.models.PurchaseOrder;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class PurchaseOrderDTO {

    private Long id;

    private String number;

    private Double amount;

    private LocalDateTime dateTime;

    private Set<PurchaseOrderProductDTO> ticketProducts;




    public PurchaseOrderDTO(PurchaseOrder purchaseOrder) {
        this.id = purchaseOrder.getId();
        this.number = purchaseOrder.getNumber();
        this.amount = purchaseOrder.getAmount();
        this.dateTime = purchaseOrder.getDateTime();
        this.ticketProducts = purchaseOrder.getTicketProducts().stream().map(ticketProduct -> new PurchaseOrderProductDTO(ticketProduct)).collect(Collectors.toSet());

    }

    public Long getId() {
        return id;
    }



    public String getNumber() {
        return number;
    }



    public Double getAmount() {
        return amount;
    }



    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Set<PurchaseOrderProductDTO> getTicketProducts() {
        return ticketProducts;
    }
}
