package com.easybuy.easybuy.controllers;

import com.easybuy.easybuy.DTO.ClientDTO;
import com.easybuy.easybuy.DTO.NewClientDTO;
import com.easybuy.easybuy.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/clients")
    public List<ClientDTO> getAll(){

        return clientService.findAll().stream().map(ClientDTO::new).collect(Collectors.toList());

    }

    @PostMapping("/clients")
    public ResponseEntity<?> create(@RequestBody NewClientDTO newClientDTO){
        try{
            clientService.createClient(newClientDTO);
            return new ResponseEntity<>("Client created succesfully", HttpStatus.CREATED);
        }catch(Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @PreAuthorize("hasRole('CLIENT')")
    @PatchMapping("/clients/current")
    public ResponseEntity<?> edit(@RequestBody NewClientDTO newClientDTO, Authentication authentication){

        try {
            clientService.editClient(newClientDTO, authentication.getName());
            return new ResponseEntity<>("edited succesfully", HttpStatus.OK);
        }catch(Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
        }

    }

    @PreAuthorize("hasRole('CLIENT')")
    @PatchMapping("/clients/current")
    public ResponseEntity<?> editPassword(Authentication authentication,@RequestParam String oldPassword, @RequestParam String newPassword){
        try {
            clientService.editClientPassword(authentication,oldPassword,newPassword);
            return new ResponseEntity<>("edited succesfully", HttpStatus.OK);
        }catch(Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

}
