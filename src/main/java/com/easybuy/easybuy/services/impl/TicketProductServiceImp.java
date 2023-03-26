package com.easybuy.easybuy.services.impl;

import com.easybuy.easybuy.DTO.ApplyProductDTO;
import com.easybuy.easybuy.models.Client;
import com.easybuy.easybuy.models.Product;
import com.easybuy.easybuy.models.Ticket;
import com.easybuy.easybuy.models.TicketProduct;
import com.easybuy.easybuy.repositories.TicketProductRepository;
import com.easybuy.easybuy.repositories.TicketRepository;
import com.easybuy.easybuy.services.ProductService;
import com.easybuy.easybuy.services.TicketProductService;
import com.easybuy.easybuy.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketProductServiceImp implements TicketProductService {

    @Autowired
    private TicketProductRepository orderProductRepository;

    @Autowired
    ProductService productService;

    @Autowired
    TicketService ticketService;

    @Autowired
    private TicketProductRepository ticketProductRepository;


    @Override
    public void save(TicketProduct ticketProduct) {

    }

    @Override
    public List<TicketProduct> findAll() {
        return orderProductRepository.findAll();
    }

    @Override
    public void createTicketProduct(List<ApplyProductDTO> products, Ticket ticket) {

        for(ApplyProductDTO applyProductDTO : products){

            TicketProduct ticketProduct = new TicketProduct(applyProductDTO.getPrice(), applyProductDTO.getQuantity());

            Optional<Product> product = productService.findById(applyProductDTO.getId());

            ticket.addTicketProduct(ticketProduct);

            ticketService.save(ticket);

            System.out.println(product);

            product.get().addTicketProduct(ticketProduct);

            productService.save(product.get());

            ticketProductRepository.save(ticketProduct);

        }
    }


}
