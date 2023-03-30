package com.easybuy.easybuy.controllers;

import com.easybuy.easybuy.DTO.ClientDTO;
import com.easybuy.easybuy.DTO.NewClientDTO;
import com.easybuy.easybuy.models.Client;
import com.easybuy.easybuy.services.ClientService;
import com.easybuy.easybuy.services.RateService;
import com.easybuy.easybuy.utils.EmailHandler;
import com.easybuy.easybuy.utils.ImageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/clients")
    public List<ClientDTO> getAll() {

        return clientService.findAll().stream().map(ClientDTO::new).collect(Collectors.toList());

    }

    @GetMapping("/clients/auth")
    public ResponseEntity<?> checkClientAuth(Authentication authentication) {

        if(authentication == null) return new ResponseEntity<>("Not authenticated", HttpStatus.UNAUTHORIZED);

        boolean isClient = authentication.getAuthorities().stream().anyMatch(autority -> Objects.equals(autority.getAuthority(), "CLIENT"));

        if(isClient) return new ResponseEntity<>("User is client", HttpStatus.OK);

        return new ResponseEntity<>("Is not a client", HttpStatus.UNAUTHORIZED);
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


    @PatchMapping("/clients/current")
    public ResponseEntity<?> edit(@RequestBody NewClientDTO newClientDTO, Authentication authentication){

        try {
            clientService.editClient(newClientDTO, authentication.getName());
            return new ResponseEntity<>("edited succesfully", HttpStatus.OK);
        }catch(Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
        }

    }


    @PatchMapping("/clients/current/password")
    public ResponseEntity<?> editPassword(Authentication authentication,@RequestParam String oldPassword, @RequestParam String newPassword){
        try {
            clientService.editClientPassword(authentication,oldPassword,newPassword);
            return new ResponseEntity<>("edited succesfully", HttpStatus.OK);
        }catch(Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/clients/auth/active")
    public ResponseEntity<?> activeClient(@RequestParam String token){

        try {
            clientService.activeClient(token);
            return new ResponseEntity<>("client actived", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }

    }

    @PostMapping("/clients/auth/resend")
    public ResponseEntity<?> resendToken(@RequestParam String email){

        try {

            clientService.resendEmail(email);

            return new ResponseEntity<>("resent email successfully", HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }



    }

    @PostMapping("/clients/auth/avatar")
    public ResponseEntity<?> resendToken(MultipartFile multipartFile, Authentication authentication){

        try {

            Client client = clientService.findByEmail(authentication.getName()).get();

            String url = ImageHandler.upload(multipartFile, client.getId() + "user");

            client.setUrlImg(url);

            clientService.save(client);

            return new ResponseEntity<>("updated avatar successfully", HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }



    }

}
