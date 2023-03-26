package com.easybuy.easybuy.services;

import com.easybuy.easybuy.DTO.ApplyProductDTO;
import com.easybuy.easybuy.DTO.CreateProductDTO;
import com.easybuy.easybuy.DTO.NewClientDTO;
import com.easybuy.easybuy.DTO.UpdateProductDTO;
import com.easybuy.easybuy.models.Client;
import com.easybuy.easybuy.models.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public void save(Product product);

    public List<Product> findAll();

    public void createProduct(CreateProductDTO createProductDTO) throws Exception;


    public void updateProduct(UpdateProductDTO updateProductDTO) throws Exception;


    public Optional<Product> findById(Long id);

    public boolean productsExists(List<ApplyProductDTO> products);

    public void uploadImages(MultipartFile[] multipartFiles, Product product) throws Exception;


}
