package com.easybuy.easybuy.controllers;

import com.easybuy.easybuy.DTO.ClientDTO;
import com.easybuy.easybuy.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/clients")
    public List<ClientDTO> getAll(){

        return clientService.findAll().stream().map(ClientDTO::new).collect(Collectors.toList());

    }

}
