package com.easybuy.easybuy.DTO;

import java.time.LocalDateTime;

public class NewTicketDTO {
private Double amount;
private LocalDateTime dateTime;

    public NewTicketDTO(Double amount, LocalDateTime dateTime) {
        this.amount = amount;
        this.dateTime = dateTime;
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
}
