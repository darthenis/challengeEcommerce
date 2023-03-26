package com.easybuy.easybuy.controllers;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RateController {


    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping("/products/{id}/rates")
    public ResponseEntity<?> addRate(Authentication authentication, @RequestBody String commentary){

        return new ResponseEntity<>("added rate succesfully", HttpStatus.CREATED);

    }

}
