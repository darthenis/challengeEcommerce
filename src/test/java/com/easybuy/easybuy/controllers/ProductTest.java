package com.easybuy.easybuy.controllers;

import com.easybuy.easybuy.DTO.CreateProductDTO;
import com.easybuy.easybuy.DTO.UpdateProductDTO;
import com.easybuy.easybuy.models.CategoriesEnum;
import com.easybuy.easybuy.models.Product;
import com.easybuy.easybuy.repositories.ProductRepository;
import com.easybuy.easybuy.services.ClientService;
import com.easybuy.easybuy.services.ProductService;
import com.easybuy.easybuy.services.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductService productService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TicketService ticketService;

    @Autowired
    ClientService clientService;
    @Autowired
    private ProductRepository productRepository;


    @WithMockUser(roles = "ADMIN")
    @Test
    @Order(3)
    public void createProduct() throws Exception {

        CreateProductDTO product = new CreateProductDTO("Television", "30 pulgadas", 1000.0, 0, List.of("url"), 20, LocalDate.now(), List.of(CategoriesEnum.VIDEO));

        mockMvc.perform(post("/api/products")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated());

        List<Product> products = productService.findAll();

        assertThat(products, hasItem(hasProperty("name", is("Television"))));

    }


    @WithMockUser(roles = "ADMIN")
    @Test
    @Order(4)
    public void patchProduct() throws Exception{

        UpdateProductDTO updateProduct = new UpdateProductDTO(1l,"tv","full hd", 1500.5, 15, null,80, LocalDate.now(), null);
        mockMvc.perform(patch("/api/products")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(updateProduct)))
                .andExpect(status().isCreated());


        List<Product> products = productService.findAll();
        assertThat(products,hasItem(hasProperty("name",is("tv"))));

    }


}
