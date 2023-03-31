package com.easybuy.easybuy.controllers;


import com.easybuy.easybuy.DTO.NewPurchaseOrderDTO;
import com.easybuy.easybuy.DTO.PayApplicationDTO;
import com.easybuy.easybuy.DTO.PurchaseOrderDTO;
import com.easybuy.easybuy.TicketUtils.PDFExporter;
import com.easybuy.easybuy.models.Client;
import com.easybuy.easybuy.models.PurchaseOrder;
import com.easybuy.easybuy.models.Ticket;
import com.easybuy.easybuy.services.*;
import com.easybuy.easybuy.utils.EmailHandler;
import com.lowagie.text.DocumentException;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PurchaseOrderController {
    @Autowired
    PurchaseService purchaseService;

    @Autowired
    ProductService productService;

    @Autowired
    PurchaseOrderProductService purchaseOrderProductService;

    @Autowired
    ClientService clientService;

    @Autowired
    EmailHandler emailHandler;

    @Autowired
    TicketService ticketService;

    @RequestMapping("/orders")
    public Set<PurchaseOrderDTO> getAll(){
        return purchaseService.findAll().stream().map(PurchaseOrderDTO::new).collect(Collectors.toSet());
    }

    @PostMapping("/client/current/orders")
    public ResponseEntity<?> newOrder(@RequestBody NewPurchaseOrderDTO newPurchaseOrderDTO, Authentication authentication) {

        if(newPurchaseOrderDTO.getAmount() == null) return new ResponseEntity<>("missing amount", HttpStatus.FORBIDDEN);

        if(newPurchaseOrderDTO.getProducts() == null || newPurchaseOrderDTO.getProducts().size() == 0) return new ResponseEntity<>("missing products", HttpStatus.FORBIDDEN);

        if(newPurchaseOrderDTO.getDateTime() == null) return new ResponseEntity<>("missing dateTime", HttpStatus.FORBIDDEN);

        if(!productService.productsExists(newPurchaseOrderDTO.getProducts())) return new ResponseEntity<>("product not found", HttpStatus.FORBIDDEN);

        PurchaseOrder purchaseOrder;

        try {

            purchaseOrder = purchaseService.createPurchaseOrder(newPurchaseOrderDTO);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }

        purchaseOrderProductService.createTicketProduct(newPurchaseOrderDTO.getProducts(), purchaseOrder);

        Optional<Client> client = clientService.findByEmail(authentication.getName());

        client.get().addTicket(purchaseOrder);

        clientService.save(client.get());

        CompletableFuture.delayedExecutor(30, TimeUnit.MINUTES).execute(() -> {
           boolean isBuyed =  purchaseService.checkPurchaseOrderState(purchaseOrder.getNumber());

           if(!isBuyed) purchaseService.delete(purchaseOrder.getNumber());
        });

        PurchaseOrder purchaseOrder1 = purchaseService.findByNumber(purchaseOrder.getNumber());

        return new ResponseEntity<>(purchaseOrder1.getId(), HttpStatus.CREATED);
    }

    @PostMapping("/client/current/orders/{id}/tickets")
    public ResponseEntity<?> completePurchaseOrder(@PathVariable Long id, Authentication authentication){

        try {

            PurchaseOrder purchaseOrder = purchaseService.completePurchase(id);

            if(purchaseOrder == null) return new ResponseEntity<>("Order not found", HttpStatus.FORBIDDEN);

            purchaseOrder.setState(true);

            Optional<Client> client = clientService.findByEmail(authentication.getName());

            Ticket ticket = ticketService.createTicket(purchaseOrder, client.get());

            PDFExporter exporter = new PDFExporter(ticket);

            //client.get().addTicketPurchase(ticket);

            //clientService.save(client.get());

            exporter.exportToRoot();

            FileSystemResource fileSystemResource = new FileSystemResource("./ticket-"+ ticket.getNumber()+".pdf");

            emailHandler.sendMailAttachment("emi.acevedo.letras@gmail.com", client.get().getEmail(), "ticket", fileSystemResource);

            FileUtils.forceDelete(fileSystemResource.getFile());

            return new ResponseEntity<>("Purchased completed successfully", HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }

    }


    @GetMapping("/client/current/tickets")
    public void exportToPDF(HttpServletResponse response , Authentication authentication,@RequestParam Long idTicket) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        Optional<Ticket> selectTicket = ticketService.findByid(idTicket);


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
