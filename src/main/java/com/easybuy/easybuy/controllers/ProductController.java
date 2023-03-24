package com.easybuy.easybuy.controllers;

import com.easybuy.easybuy.DTO.ProductDTO;
import com.easybuy.easybuy.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public List<ProductDTO> getAll(){

        return productService.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());

    }

    @PostMapping("/current/product/new")
    public ResponseEntity<Object> createProduct(Authentication authentication, @RequestBody )


}
