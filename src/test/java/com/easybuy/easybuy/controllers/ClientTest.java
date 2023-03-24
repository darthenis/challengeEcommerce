package com.easybuy.easybuy.controllers;

import com.easybuy.easybuy.DTO.NewClientDTO;
import com.easybuy.easybuy.models.Client;
import com.easybuy.easybuy.services.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
public class ClientTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    ClientService clientService;

    @Test
    public void createClient() throws Exception{

        NewClientDTO client = new NewClientDTO("melba", "Gallo", "123312", "melba@mindhub.com", "asd");

        mockMvc.perform(post("/api/clients")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isCreated());

        List<Client> clients = clientService.findAll();

        assertThat(clients, hasItem(hasProperty("name", is("melba"))));

    }

    @WithMockUser(username="melba@mindhub.com", roles = "CLIENT")
    @Test
    public void editClient() throws Exception{

        NewClientDTO client = new NewClientDTO("emi", "Gallo", "123312", "melba@mindhub.com", "null");

        mockMvc.perform(patch("/api/clients/current")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isOk());

        List<Client> clients = clientService.findAll();
        assertThat(clients, hasItem(hasProperty("name", is("emi"))));
    }

}
