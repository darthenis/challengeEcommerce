package com.easybuy.easybuy.services.impl;

import com.easybuy.easybuy.DTO.CreateProductDTO;
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

    @Override
    public void createProduct(CreateProductDTO createProductDTO) throws Exception {

            if(createProductDTO.getDiscount() < 0 ) throw new Exception("The number entered is wrong");

            if(createProductDTO.getName().isEmpty()) throw new Exception("missing Name");

            if(createProductDTO.getDescription().isEmpty()) throw new Exception("missing description");

            if(createProductDTO.getDate() == null ) throw new Exception("missing date");

            if(createProductDTO.getPrice() == null) throw new Exception("missing price");

            if(createProductDTO.getImgUrl() == null) throw new Exception("missing Image");

            if(createProductDTO.getStock() <= 0) throw new Exception("The number entered is wrong");



            Product newProduct = new Product(createProductDTO.getName(), createProductDTO.getDescription(), createProductDTO.getPrice(), createProductDTO.getDiscount(), createProductDTO.getImgUrl(),createProductDTO.getStock(),createProductDTO.getDate(),createProductDTO.getCategories());

          productRepository.save(newProduct);

        }



}
