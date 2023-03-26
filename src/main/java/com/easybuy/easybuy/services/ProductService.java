package com.easybuy.easybuy.services;

import com.easybuy.easybuy.DTO.CreateProductDTO;
import com.easybuy.easybuy.DTO.NewClientDTO;
import com.easybuy.easybuy.DTO.UpdateProductDTO;
import com.easybuy.easybuy.models.Product;

import java.util.List;

public interface ProductService {

    public void save(Product product);

    public List<Product> findAll();

    public void createProduct(CreateProductDTO createProductDTO) throws Exception;

    public void updateProduct(CreateProductDTO createProductDTO) throws Exception;

    void updateProduct(UpdateProductDTO updateProductDTO) throws Exception;
}
