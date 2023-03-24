package com.easybuy.easybuy.services;

import com.easybuy.easybuy.models.Product;

import java.util.List;

public interface ProductService {

    public void save(Product product);

    public List<Product> findAll();

}
