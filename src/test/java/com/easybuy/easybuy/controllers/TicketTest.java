package com.easybuy.easybuy.controllers;

import com.easybuy.easybuy.DTO.ApplyProductDTO;
import com.easybuy.easybuy.DTO.NewClientDTO;
import com.easybuy.easybuy.DTO.NewTicketDTO;
import com.easybuy.easybuy.models.Ticket;
import com.easybuy.easybuy.services.ProductService;
import com.easybuy.easybuy.services.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.time.LocalDateTime;
import java.util.List;

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
    ObjectMapper objectMapper;


    @Order(4)
    @WithMockUser(roles = "CLIENT", username = "melba@mindhub.com")
    @Test
    public void CreateTicketOK() throws Exception {

        System.out.println("elements:  " + productService.findAll().size());

        NewTicketDTO newTicketDTO = new NewTicketDTO(LocalDateTime.now(), List.of(new ApplyProductDTO(1L, 12.0, 2)));

        mockMvc.perform(post("/api/client/current/ticket")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newTicketDTO)))
                .andExpect(status().isCreated());

        List<Ticket> ticket= ticketService.findAll();
        assertThat(ticket, hasItem(hasProperty("number", is("001-000001"))));

    }



}
