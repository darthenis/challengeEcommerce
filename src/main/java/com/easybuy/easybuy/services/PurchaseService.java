package com.easybuy.easybuy.services;

import com.easybuy.easybuy.DTO.NewPurchaseOrderDTO;
import com.easybuy.easybuy.models.PurchaseOrder;

import java.util.List;
import java.util.Optional;

public interface PurchaseService {

    public void save(PurchaseOrder purchaseOrder);

    public List<PurchaseOrder> findAll();

    public PurchaseOrder findByNumber (String number);

    public PurchaseOrder createPurchaseOrder(NewPurchaseOrderDTO newPurchaseOrderDTO) throws Exception;

    public Long findByMaxId();

    public Optional<PurchaseOrder> findById (Long id);

    public boolean checkPurchaseOrderState(String number);

    public void delete(String number);

    public PurchaseOrder completePurchase(Long id) throws Exception;

}
