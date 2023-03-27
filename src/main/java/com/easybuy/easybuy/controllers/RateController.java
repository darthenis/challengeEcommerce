package com.easybuy.easybuy.controllers;

import com.easybuy.easybuy.models.Client;
import com.easybuy.easybuy.models.StarsEnum;
import com.easybuy.easybuy.services.ClientService;
import com.easybuy.easybuy.services.RateService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RateController {

    @Autowired
    RateService rateService;

    @Autowired
    ClientService clientService;


    @PostMapping("/products/{id}/rates")
    public ResponseEntity<?> addRate(Authentication authentication, @RequestParam String commentary, @RequestParam StarsEnum starsEnum, @PathVariable Long id) {

        Optional<Client> client = clientService.findByEmail(authentication.getName());

        try {

            rateService.addRate(id, commentary, starsEnum, client.get());

            return new ResponseEntity<>("added rate succesfully", HttpStatus.CREATED);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }



    }

}
