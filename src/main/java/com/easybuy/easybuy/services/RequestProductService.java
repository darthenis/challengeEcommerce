package com.easybuy.easybuy.services;

import com.easybuy.easybuy.DTO.ApplyProductDTO;
import com.easybuy.easybuy.models.PurchaseOrder;
import com.easybuy.easybuy.models.PurchaseOrderProduct;

import java.util.List;

public interface RequestProductService {
    void save (PurchaseOrderProduct purchaseOrderProduct);

    List<PurchaseOrderProduct> findAll() ;

    void createTicketProduct(List<ApplyProductDTO> products, PurchaseOrder purchaseOrder);
}
