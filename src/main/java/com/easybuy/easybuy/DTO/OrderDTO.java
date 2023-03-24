package com.easybuy.easybuy.DTO;

import com.easybuy.easybuy.models.Order;
import com.easybuy.easybuy.models.Product;

import java.time.LocalDateTime;

public class OrderDTO {

    private Long id;

    private String number;

    private Double amount;

    private LocalDateTime dateTime;

    private Order order;

    private Product product;

}
