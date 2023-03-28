package com.easybuy.easybuy.services.impl;

import com.easybuy.easybuy.DTO.ApplyProductDTO;
import com.easybuy.easybuy.models.Product;
import com.easybuy.easybuy.models.PurchaseOrder;
import com.easybuy.easybuy.models.PurchaseOrderProduct;
import com.easybuy.easybuy.repositories.PurchaseOrderProductRepository;
import com.easybuy.easybuy.services.ProductService;
import com.easybuy.easybuy.services.RequestProductService;
import com.easybuy.easybuy.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderProductServiceImp implements RequestProductService {

    @Autowired
    private PurchaseOrderProductRepository orderProductRepository;

    @Autowired
    ProductService productService;

    @Autowired
    RequestService requestService;

    @Autowired
    private PurchaseOrderProductRepository purchaseOrderProductRepository;


    @Override
    public void save(PurchaseOrderProduct purchaseOrderProduct) {

    }

    @Override
    public List<PurchaseOrderProduct> findAll() {
        return orderProductRepository.findAll();
    }

    @Override
    public void createTicketProduct(List<ApplyProductDTO> products, PurchaseOrder purchaseOrder) {

        for(ApplyProductDTO applyProductDTO : products){

            PurchaseOrderProduct purchaseOrderProduct = new PurchaseOrderProduct(applyProductDTO.getPrice(), applyProductDTO.getQuantity());

            Optional<Product> product = productService.findById(applyProductDTO.getProductId());

            purchaseOrder.addTicketProduct(purchaseOrderProduct);

            requestService.save(purchaseOrder);

            product.get().addTicketProduct(purchaseOrderProduct);

            productService.save(product.get());

            purchaseOrderProductRepository.save(purchaseOrderProduct);

        }
    }


}
