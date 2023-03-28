package com.easybuy.easybuy.services.impl;

import com.easybuy.easybuy.DTO.ApplyProductDTO;
import com.easybuy.easybuy.DTO.NewPurchaseOrderDTO;
import com.easybuy.easybuy.models.PurchaseOrder;
import com.easybuy.easybuy.models.PurchaseOrderProduct;
import com.easybuy.easybuy.repositories.PurchaseOrderRepository;
import com.easybuy.easybuy.services.ProductService;
import com.easybuy.easybuy.services.PurchaseOrderProductService;
import com.easybuy.easybuy.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderServiceImpl implements PurchaseService {

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    ProductService productService;

    @Lazy
    @Autowired
    PurchaseOrderProductService purchaseOrderProductService;

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
    public boolean checkPurchaseOrderState(String number) {

        PurchaseOrder purchaseOrder = purchaseOrderRepository.findByNumber(number);

        return purchaseOrder.isState();

    }

    @Override
    @Transactional
    public void delete(String number) {

        PurchaseOrder purchaseOrder = purchaseOrderRepository.findByNumber(number);

        for(PurchaseOrderProduct purchaseOrderProduct : purchaseOrder.getTicketProducts()){

            productService.sumStock(purchaseOrderProduct.getProduct().getId(), purchaseOrderProduct.getQuantity());

            purchaseOrderProductService.deleteById(purchaseOrderProduct.getId());

        }

        purchaseOrderRepository.deleteById(purchaseOrder.getId());
    }

    @Override
    public PurchaseOrder completePurchase(Long id) throws Exception {

        Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findById(id);

        if(purchaseOrder.isEmpty()) throw new Exception("PurchaseOrder not found");

        purchaseOrder.get().setState(true);

        purchaseOrderRepository.save(purchaseOrder.get());

        return purchaseOrder.get();

    }

    @Override
    public PurchaseOrder createPurchaseOrder(NewPurchaseOrderDTO newPurchaseOrderDTO) throws Exception {

        if(newPurchaseOrderDTO.getDateTime() == null ) throw new Exception("missing date");

        if(newPurchaseOrderDTO.getProducts() == null ) throw new Exception("missing products");

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



        PurchaseOrder newPurchaseOrder = new PurchaseOrder(finalTicketNumber, newPurchaseOrderDTO.getAmount(), newPurchaseOrderDTO.getDateTime());



        double amount = 0.0;

        for(ApplyProductDTO applyProductDTO : newPurchaseOrderDTO.getProducts()){

            productService.restStock(applyProductDTO.getProductId(), applyProductDTO.getQuantity());

            double total = applyProductDTO.getPrice() * applyProductDTO.getQuantity();

            amount += total;

        }

        purchaseOrderRepository.save(newPurchaseOrder);


        return newPurchaseOrder;

    }


}
