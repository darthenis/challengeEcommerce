package com.easybuy.easybuy.services.impl;

import com.easybuy.easybuy.models.*;
import com.easybuy.easybuy.repositories.RateRepository;
import com.easybuy.easybuy.repositories.PurchaseOrderRepository;
import com.easybuy.easybuy.services.ClientService;
import com.easybuy.easybuy.services.ProductService;
import com.easybuy.easybuy.services.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RateServiceImpl implements RateService {

    @Autowired
    RateRepository rateRepository;

    @Autowired
    ProductService productService;

    @Autowired
    ClientService clientService;
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

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
        Set<PurchaseOrder> purchaseOrders = client.getTickets();

        Set<PurchaseOrderProduct> purchaseOrderProducts = new HashSet<>();

        for(PurchaseOrder purchaseOrder : purchaseOrders){
            purchaseOrderProducts.addAll(purchaseOrder.getTicketProducts());
        }
        boolean exist = purchaseOrderProducts.stream().anyMatch(ticketProduct -> Objects.equals(ticketProduct.getProduct().getId(), productId));

        if(!exist){
            throw new Exception("product not found");
        }

        if (client.getRates().stream().noneMatch(rate -> rate.getProduct().getId() == productId)) {
            Rate newRate = new Rate(commentary, starsEnum);

            client.addRate(newRate);

            Optional<Product> product = productService.findById(productId);

            product.get().addRate(newRate);

            clientService.save(client);
            productService.save(product.get());
            rateRepository.save(newRate);
        }else {
            throw new Exception("You have already commmented on this product");
        }
    }
}
