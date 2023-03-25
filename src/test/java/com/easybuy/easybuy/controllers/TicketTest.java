package com.easybuy.easybuy.controllers;

import com.easybuy.easybuy.DTO.NewTicketDTO;
import com.easybuy.easybuy.models.Ticket;
import com.easybuy.easybuy.services.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class TicketTest {

    @Autowired
    private TicketService ticketService;

        @Test
        public void CreateTicketOK() throws Exception {
            ticketService.createTicket(new NewTicketDTO(1000.0,LocalDateTime.now()));
            List <Ticket> ticket= ticketService.findAll();
            assertThat(ticket, hasItem(hasProperty("number", is("001-000001"))));
        }




}
