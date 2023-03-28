package com.easybuy.easybuy.services.impl;

import com.easybuy.easybuy.DTO.ApplyProductDTO;
import com.easybuy.easybuy.DTO.NewTicketDTO;
import com.easybuy.easybuy.models.PurchaseOrder;
import com.easybuy.easybuy.repositories.PurchaseOrderRepository;
import com.easybuy.easybuy.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderServiceImpl implements RequestService {

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;

    @Override
    public void save(PurchaseOrder purchaseOrder) {
        purchaseOrderRepository.save(purchaseOrder);
    }

    @Override
    public List<PurchaseOrder> findAll() {
        return purchaseOrderRepository.findAll();
    }

    @Override
    public PurchaseOrder findByNumber(String number) {
        return purchaseOrderRepository.findByNumber(number);
    }


    @Override
    public Long findByMaxId() {
        return purchaseOrderRepository.findMaxId();
    }

    @Override
    public Optional<PurchaseOrder> findById(Long id) {
        return purchaseOrderRepository.findById(id);
    }

    @Override
    public PurchaseOrder createTicket(NewTicketDTO newTicketDTO) throws Exception {

        if(newTicketDTO.getDateTime() == null ) throw new Exception("missing date");

        if(newTicketDTO.getProducts() == null ) throw new Exception("missing products");

        int serialNumber = 1;
        Long maxId = purchaseOrderRepository.findMaxId();
        String serial;
        String ticketNumber;
        String finalTicketNumber;

        if (maxId == null){
            maxId = 1L;
        }else {
            maxId += 1L;
        }

        while(maxId > 99999){
            maxId -= 99999;
            serialNumber++;
        }

        serial = String.format("%03d", serialNumber);
        ticketNumber = String.format("%06d", maxId);
        finalTicketNumber = serial + "-" +ticketNumber;

        PurchaseOrder newPurchaseOrder = new PurchaseOrder(finalTicketNumber,newTicketDTO.getAmount(),newTicketDTO.getDateTime());
        purchaseOrderRepository.save(newPurchaseOrder);

        double amount = 0.0;

        for(ApplyProductDTO applyProductDTO : newTicketDTO.getProducts()){

            double total = applyProductDTO.getPrice() * applyProductDTO.getQuantity();

            amount += total;

        }



        return new PurchaseOrder(finalTicketNumber, amount, newTicketDTO.getDateTime());

    }


}
