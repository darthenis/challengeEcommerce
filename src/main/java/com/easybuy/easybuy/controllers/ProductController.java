package com.easybuy.easybuy.controllers;

import com.easybuy.easybuy.DTO.CreateProductDTO;
import com.easybuy.easybuy.DTO.ProductDTO;
import com.easybuy.easybuy.DTO.UpdateProductDTO;
import com.easybuy.easybuy.models.Product;
import com.easybuy.easybuy.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
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

    @GetMapping("/products/{id}")
    public ProductDTO gtByID(@PathVariable Long id){
        return  new ProductDTO(productService.findById(id).get());
    }

    @PostMapping("/products")
    public ResponseEntity<?> createProduct( @RequestBody CreateProductDTO createProductDTO){
        try{
            productService.createProduct(createProductDTO);
            return new ResponseEntity<>("Product created succesfully", HttpStatus.CREATED);
        }catch(Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
        }
    }


    @PatchMapping("/products")
    public ResponseEntity<?> patchProduct(@RequestBody UpdateProductDTO updateProductDTO) throws Exception{
        try{
            productService.updateProduct(updateProductDTO);
            return new ResponseEntity<>("Product update succesfully", HttpStatus.CREATED);
        }catch(Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/products/{id}/images")
    public ResponseEntity<?> uploadImages(@RequestParam() MultipartFile[] images, @PathVariable Long id) {

        Optional<Product> product =  productService.findById(id);

        if(images.length > 4) return new ResponseEntity<>("Only upload until 4 images", HttpStatus.FORBIDDEN);

        if(product.isEmpty()) return new ResponseEntity<>("product not found", HttpStatus.FORBIDDEN);

        try {
            productService.uploadImages(images, product.get());
            return new ResponseEntity<>("uploaded sucessfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("error uploading", HttpStatus.FORBIDDEN);
        }

    }


    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteImg(@PathVariable Long id, @RequestParam String url){

        if(id == null) return new ResponseEntity<>("missing id", HttpStatus.FORBIDDEN);

        if(url == null) return new ResponseEntity<>("missing url", HttpStatus.FORBIDDEN);

        try {
            productService.deleteImage(url, id);
            return new ResponseEntity<>("deleted succesfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }

    }

    @DeleteMapping("/current/products")
    public ResponseEntity<?> delete(@RequestParam Long id){
        if(id == null) return new ResponseEntity<>("missing id", HttpStatus.FORBIDDEN);

        try{
            productService.deleteProduct(id);
            return new ResponseEntity<>("deleted succesfully", HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/products/last")
    public List<ProductDTO> getLast(){

        return productService.findLastTenProducts().stream().map(ProductDTO::new).collect(Collectors.toList());

    }

}
