package com.easybuy.easybuy.controllers;

import com.easybuy.easybuy.DTO.CreateProductDTO;
import com.easybuy.easybuy.DTO.ProductDTO;
import com.easybuy.easybuy.DTO.PurchaseDTO;
import com.easybuy.easybuy.DTO.UpdateProductDTO;
import com.easybuy.easybuy.models.*;
import com.easybuy.easybuy.services.ClientService;
import com.easybuy.easybuy.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ClientService clientService;


    @GetMapping("/products")
    public List<ProductDTO> getAll(){
        return productService.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/clients/products")
    public List<ProductDTO> getProductsAvalible(){

        List<Product> products = productService.findAll().stream().filter(Product::getStatus).collect(Collectors.toList());

        return products.stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/products/{id}")
    public ProductDTO getByID(@PathVariable Long id){
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
    public ResponseEntity<?> uploadImages(MultipartHttpServletRequest request, @PathVariable Long id) {

        Optional<Product> product =  productService.findById(id);

        Map<String, MultipartFile> fileMap = request.getFileMap();

        if(fileMap.size() > 4) return new ResponseEntity<>("Only upload until 4 images", HttpStatus.FORBIDDEN);

        if(product.isEmpty()) return new ResponseEntity<>("product not found", HttpStatus.FORBIDDEN);

        try {
            productService.uploadImages(fileMap, product.get());
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

    @PostMapping("/current/products/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(id == null) return new ResponseEntity<>("missing id", HttpStatus.FORBIDDEN);

        try{
            productService.deleteProduct(id);
            return new ResponseEntity<>("deleted succesfully", HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/products/last/updated")
    public List<ProductDTO> getLastUpdated(){

        return productService.findLast4ProductsUpdated().stream().map(ProductDTO::new).collect(Collectors.toList());

    }

    @GetMapping("/products/last/offers")
    public List<ProductDTO> getLasOffer(){

        return productService.findBest4Offers().stream().map(ProductDTO::new).collect(Collectors.toList());

    }

    @GetMapping("/client/current/products")
    public List<PurchaseDTO> getBuyedProducts(Authentication authentication){

        Client client = clientService.findByEmail(authentication.getName()).get();

        List<TicketProduct> ticketProducts = new ArrayList<>();

        List<PurchaseDTO> purchaseDTOS = new ArrayList<>();

        for(Ticket ticket : client.getTicketsPurchase()){

            for(TicketProduct ticketProduct : ticket.getTicketProducts()){

                Optional<Product> product = productService.findById(ticketProduct.getProductId());
                PurchaseDTO purchaseDTO = new PurchaseDTO(product.get());
                purchaseDTO.setDate(ticket.getDateTime());

                boolean rated = client.getRates().stream().anyMatch(rate -> Objects.equals(rate.getProduct().getId(), product.get().getId()));

                purchaseDTO.setRated(rated);

                purchaseDTOS.add(purchaseDTO);
            }

        }

        return purchaseDTOS;

    }

}
