package com.easybuy.easybuy.controllers;


import com.easybuy.easybuy.DTO.NewTicketDTO;
import com.easybuy.easybuy.DTO.TicketDTO;
import com.easybuy.easybuy.DTO.TicketProductDTO;
import com.easybuy.easybuy.TicketUtils.PDFExporter;
import com.easybuy.easybuy.models.Client;
import com.easybuy.easybuy.models.Ticket;
import com.easybuy.easybuy.models.TicketProduct;
import com.easybuy.easybuy.services.ClientService;
import com.easybuy.easybuy.services.ProductService;
import com.easybuy.easybuy.services.TicketProductService;
import com.easybuy.easybuy.services.TicketService;
import com.easybuy.easybuy.utils.EmailHandler;
import com.lowagie.text.DocumentException;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @Autowired
    ProductService productService;

    @Autowired
    TicketProductService ticketProductService;

    @Autowired
    ClientService clientService;

    @Autowired
    EmailHandler emailHandler;


    @RequestMapping("/orders")
    public Set<TicketDTO> getAll(){
        return ticketService.findAll().stream().map(TicketDTO::new).collect(Collectors.toSet());
    }

    @PostMapping("/client/current/tickets")
    public ResponseEntity<Object> newTicket(@RequestBody NewTicketDTO newTicketDTO, Authentication authentication) throws Exception {

        if(!productService.productsExists(newTicketDTO.getProducts())) return new ResponseEntity<>("product not found", HttpStatus.FORBIDDEN);

        Ticket ticket = ticketService.createTicket(newTicketDTO);

        ticketProductService.createTicketProduct(newTicketDTO.getProducts(), ticket);

        Optional<Client> client = clientService.findByEmail(authentication.getName());

        client.get().addTicket(ticket);

        clientService.save(client.get());

        PDFExporter exporter = new PDFExporter(ticket);

        exporter.exportToRoot();

        FileSystemResource fileSystemResource = new FileSystemResource("./ticket"+ticket.getNumber()+".pdf");

        emailHandler.sendMailAttachment("emi.acevedo.letras@gmail.com", client.get().getEmail(), "ticket", fileSystemResource);

        FileUtils.forceDelete(fileSystemResource.getFile());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/client/current/pdf")
    public void exportToPDF(HttpServletResponse response , Authentication authentication,@RequestParam Long idTicket) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        Optional<Ticket> selectTicket = ticketService.findById(idTicket);


        if (idTicket == null) {
            response.sendError(403, "Missing ID");
        } else if (selectTicket.isEmpty()) {
            response.sendError(403, "Ticket not found");
        } else {
            PDFExporter exporter = new PDFExporter(selectTicket.get());

            exporter.export(response);
        }

    }

}
