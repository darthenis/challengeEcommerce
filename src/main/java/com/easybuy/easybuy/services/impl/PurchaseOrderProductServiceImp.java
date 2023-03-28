package com.easybuy.easybuy.services.impl;

import com.easybuy.easybuy.DTO.ApplyProductDTO;
import com.easybuy.easybuy.models.Product;
import com.easybuy.easybuy.models.PurchaseOrder;
import com.easybuy.easybuy.models.PurchaseOrderProduct;
import com.easybuy.easybuy.repositories.PurchaseOrderProductRepository;
import com.easybuy.easybuy.services.ProductService;
import com.easybuy.easybuy.services.PurchaseOrderProductService;
import com.easybuy.easybuy.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderProductServiceImp implements PurchaseOrderProductService {

    @Autowired
    private PurchaseOrderProductRepository orderProductRepository;

    @Autowired
    ProductService productService;

    @Autowired
    PurchaseService purchaseService;

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

            purchaseService.save(purchaseOrder);

            product.get().addTicketProduct(purchaseOrderProduct);

            productService.save(product.get());

            purchaseOrderProductRepository.save(purchaseOrderProduct);

        }
    }

    @Override
    public void deleteById(Long id) {
        purchaseOrderProductRepository.deleteById(id);
    }


}
