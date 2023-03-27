package com.easybuy.easybuy.services.impl;

import com.easybuy.easybuy.DTO.ApplyProductDTO;
import com.easybuy.easybuy.DTO.CreateProductDTO;
import com.easybuy.easybuy.DTO.UpdateProductDTO;
import com.easybuy.easybuy.models.Client;
import com.easybuy.easybuy.models.Product;
import com.easybuy.easybuy.repositories.ProductRepository;
import com.easybuy.easybuy.services.ProductService;
import com.easybuy.easybuy.utils.ImageHandler;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<Product> findById(Long id) {return productRepository.findById(id);}

    @Override
    public boolean productsExists(List<ApplyProductDTO> products) {
        for(ApplyProductDTO product : products){

            if(!productRepository.existsById(product.getId())){

                return false;
            }

        }

        return true;
    }

    @Override
    public void uploadImages(MultipartFile[] multipartFiles, Product product) {

        List<String> urls = product.getImgsUrls();

        for(MultipartFile multipartFile : multipartFiles){

            if(urls.size() < 4){

                String url = ImageHandler.upload(multipartFile, product.getId() + "-" + urls.size());

                urls.add(url);

            }

        }

        product.setImgsUrls(urls);

        productRepository.save(product);

    }

    @Override
    public void deleteImage(String url, Long id) throws Exception {

        Optional<Product> product = productRepository.findById(id);

        if(product.isEmpty()) throw new Exception("product not found");

        List<String> urls = product.get().getImgsUrls();

        if(!urls.remove(url)) throw new Exception("url not found");

    }

    @Override
    public void createProduct(CreateProductDTO createProductDTO) throws Exception {

            if(createProductDTO.getDiscount() < 0 ) throw new Exception("The number entered is wrong");

            if(createProductDTO.getName().isEmpty()) throw new Exception("missing Name");

            if(createProductDTO.getDescription().isEmpty()) throw new Exception("missing description");

            if(createProductDTO.getDate() == null ) throw new Exception("missing date");

            if(createProductDTO.getPrice() == null) throw new Exception("missing price");

            if(createProductDTO.getStock() < 1) throw new Exception("The number entered is wrong");

            Product newProduct = new Product(createProductDTO.getName(), createProductDTO.getDescription(), createProductDTO.getPrice(), createProductDTO.getDiscount(),createProductDTO.getStock(),createProductDTO.getDate(),createProductDTO.getCategories());

          productRepository.save(newProduct);

        }



    @Override
    public void updateProduct(UpdateProductDTO updateProductDTO) throws Exception {
        Product selectProduct  ;
        boolean isEmpty = true;
        selectProduct = productRepository.findById(updateProductDTO.getId()).orElse(null);

        if(selectProduct == null){
            throw new Exception("The product Doesn't exist");
        }
        if (!updateProductDTO.getName().isEmpty()){
            selectProduct.setName(updateProductDTO.getName());
            isEmpty = false;
        }
        if (!updateProductDTO.getDescription().isEmpty()){
            selectProduct.setDescription(updateProductDTO.getDescription());
            isEmpty = false;
        }
        if (updateProductDTO.getPrice() != null){
            selectProduct.setPrice(updateProductDTO.getPrice());
            isEmpty = false;
        }
        if (updateProductDTO.getDiscount() >= 0){
            selectProduct.setDiscount(updateProductDTO.getDiscount());
            isEmpty = false;
        }
        if (updateProductDTO.getImgUrl() != null){
            selectProduct.setImgsUrls(updateProductDTO.getImgUrl());
            isEmpty = false;
        }
        if (updateProductDTO.getStock() >= 0){
            selectProduct.setStock(updateProductDTO.getStock());
            isEmpty = false;
        }
        if (updateProductDTO.getCategories() != null){
            selectProduct.setCategoriesEnums(updateProductDTO.getCategories());
            isEmpty = false;
        }

        if (isEmpty) throw new Exception("You haven't entered any data");
        else productRepository.save(selectProduct);
    }


}
