package com.easybuy.easybuy.controllers;

import com.easybuy.easybuy.DTO.ApplyProductDTO;
import com.easybuy.easybuy.DTO.NewClientDTO;
import com.easybuy.easybuy.DTO.NewTicketDTO;
import com.easybuy.easybuy.configuration.ApplicationContextProvider;
import com.easybuy.easybuy.models.CategoriesEnum;
import com.easybuy.easybuy.models.Client;
import com.easybuy.easybuy.models.Product;
import com.easybuy.easybuy.models.Ticket;
import com.easybuy.easybuy.repositories.ClientRepository;
import com.easybuy.easybuy.repositories.ProductRepository;
import com.easybuy.easybuy.services.ClientService;
import com.easybuy.easybuy.services.ProductService;
import com.easybuy.easybuy.services.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.context.assertj.ApplicationContextAssert;
import org.springframework.boot.test.context.assertj.ApplicationContextAssertProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TicketTest {

    @Autowired
    TicketService ticketService;

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


    @Order(5)
    @WithMockUser(roles = "CLIENT", username = "julio@mindhub.com")
    @Test
    public void CreateTicketOK() throws Exception {

        clientRepository.save(new Client("julio", "Alvarez", "123123", "julio@mindhub.com", "asd"));

        productRepository.save(new Product("Television", "30 pulgadas", 1000.0, 0, List.of("url"), 20, LocalDate.now(), List.of(CategoriesEnum.AUDIONVIDEO)));

        NewTicketDTO newTicketDTO = new NewTicketDTO(LocalDateTime.now(),1500.0, List.of(new ApplyProductDTO(1L, 12.0, 2)));

        mockMvc.perform(post("/api/client/current/ticket")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newTicketDTO)))
                .andExpect(status().isCreated());

        List<Ticket> ticket = ticketService.findAll();

        assertThat(ticket, hasItem(hasProperty("number", is("001-000001"))));

    }



}
