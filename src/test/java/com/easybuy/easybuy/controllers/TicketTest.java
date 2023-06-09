package com.easybuy.easybuy.controllers;

import com.easybuy.easybuy.DTO.ApplyProductDTO;
import com.easybuy.easybuy.DTO.NewPurchaseOrderDTO;
import com.easybuy.easybuy.models.*;
import com.easybuy.easybuy.models.PurchaseOrder;
import com.easybuy.easybuy.repositories.ClientRepository;
import com.easybuy.easybuy.repositories.ProductRepository;
import com.easybuy.easybuy.repositories.RateRepository;
import com.easybuy.easybuy.services.ClientService;
import com.easybuy.easybuy.services.ProductService;
import com.easybuy.easybuy.services.PurchaseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TicketTest {

    @Autowired
    PurchaseService purchaseService;

    @Autowired
    ProductService productService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ClientService clientService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    RateRepository rateRepository;


    @org.junit.jupiter.api.Order(5)
    @WithMockUser(roles = "CLIENT", username = "julio@mindhub.com")
    @Test
    public void CreateTicketOK() throws Exception {

        clientRepository.save(new Client("julio", "Alvarez", "123123", "julio@mindhub.com", "asd"));

        productRepository.save(new Product("Television", "30 pulgadas", 1000.0, 0, 20, LocalDate.now(), List.of(CategoriesEnum.VIDEO)));

        /*NewPurchaseOrderDTO newPurchaseOrderDTO = new NewPurchaseOrderDTO(LocalDateTime.now(),1500.0, List.of(new ApplyProductDTO(1L, 12.0, 2)));

        mockMvc.perform(post("/api/client/current/ticket")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newPurchaseOrderDTO)))
                .andExpect(status().isCreated());

        List<PurchaseOrder> purchaseOrder = purchaseService.findAll();

        assertThat(purchaseOrder, hasItem(hasProperty("number", is("001-000001"))));*/

    }

    @WithMockUser(roles = "CLIENT", username = "julio@mindhub.com")
    @Test
    @org.junit.jupiter.api.Order(7)
    public void addRate() throws Exception {

        mockMvc.perform(post("/api/products/1/rates")
                        .contentType("application/json")
                        .param("commentary", "testing commentary")
                        .param("starsEnum", "THREE"))
                .andExpect(status().isCreated());

        List<Rate> rates = rateRepository.findAll();

        assertThat(rates, is(not(empty())));

    }



}
