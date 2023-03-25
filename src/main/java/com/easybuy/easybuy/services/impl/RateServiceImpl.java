package com.easybuy.easybuy.services.impl;

import com.easybuy.easybuy.models.*;
import com.easybuy.easybuy.repositories.RateRepository;
import com.easybuy.easybuy.repositories.TicketRepository;
import com.easybuy.easybuy.services.ClientService;
import com.easybuy.easybuy.services.ProductService;
import com.easybuy.easybuy.services.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RateServiceImpl implements RateService {

    @Autowired
    RateRepository rateRepository;

    @Autowired
    ProductService productService;

    @Autowired
    ClientService clientService;
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public void save(Rate rate) {
        rateRepository.save(rate);
    }

    @Override
    public List<Rate> findAll() {
        return rateRepository.findAll();
    }

    @Override
    public void addRate(Long productId, String commentary, StarsEnum starsEnum, Client client) throws Exception{
        Set<Ticket> tickets = client.getTickets();
        Set<TicketProduct> ticketProducts = new HashSet<>();
        for(Ticket ticket : tickets){
            ticketProducts.addAll(ticket.getTicketProducts());
        }
        boolean exist = ticketProducts.stream().anyMatch(ticketProduct -> Objects.equals(ticketProduct.getProduct().getId(), productId));

        if(!exist){
            throw new Exception("product not found");
        }

        Rate newRate = new Rate(commentary, starsEnum);

        client.addRate(newRate);

        Optional<Product> product = productService.findById(productId);

        product.get().addRate(newRate);

        clientService.save(client);

        productService.save(product.get());

        rateRepository.save(newRate);

    }
}
