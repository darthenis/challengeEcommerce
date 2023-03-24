package com.easybuy.easybuy.services.impl;

import com.easybuy.easybuy.models.Product;
import com.easybuy.easybuy.repositories.ProductRepository;
import com.easybuy.easybuy.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
