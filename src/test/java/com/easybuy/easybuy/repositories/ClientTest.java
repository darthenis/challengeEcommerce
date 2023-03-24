package com.easybuy.easybuy.repositories;

import com.easybuy.easybuy.DTO.NewClientDTO;
import com.easybuy.easybuy.models.Client;
import com.easybuy.easybuy.services.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

@SpringBootTest
public class ClientTest {


    @Autowired
    ClientService clientService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void createClient() throws Exception{

        clientService.createClient(new NewClientDTO("melba", "Gallo", "123312", "melba@mindhub.com", passwordEncoder.encode("asd")));

        List<Client> clients = clientService.findAll();

        assertThat(clients, is(not(empty())));

    }

    @Test
    public void editClient() throws Exception{

        clientService.editClient(new NewClientDTO(null, "pollo", null, null, null), "melba@mindhub.com");

        List<Client> clients = clientService.findAll();

        assertThat(clients, hasItem(hasProperty("lastName", is("pollo"))));
    }
}
