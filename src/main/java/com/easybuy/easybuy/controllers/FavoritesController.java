package com.easybuy.easybuy.controllers;

import com.easybuy.easybuy.DTO.FavoriteApplyDTO;
import com.easybuy.easybuy.DTO.FavoriteDTO;
import com.easybuy.easybuy.models.Client;
import com.easybuy.easybuy.models.Favorite;
import com.easybuy.easybuy.services.ClientService;
import com.easybuy.easybuy.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class FavoritesController {

    @Autowired
    FavoriteService favoriteService;

    @Autowired
    ClientService clientService;

    @GetMapping("/client/current/favorites")
    public List<FavoriteDTO> getFavorites(Authentication authentication){

       Optional<Client> client = clientService.findByEmail(authentication.getName());

       return client.get().getFavorites().stream().map(FavoriteDTO::new).collect(Collectors.toList());

    }

    @PostMapping("/client/current/favorites")
    public ResponseEntity<?> addFavorites(Authentication authentication, @RequestBody FavoriteApplyDTO favoriteApplyDTO){

        if(favoriteApplyDTO.getName() == null) return new ResponseEntity<>("missing name", HttpStatus.FORBIDDEN);

        if(favoriteApplyDTO.getPrice() == null) return new ResponseEntity<>("missing price", HttpStatus.FORBIDDEN);

        if(favoriteApplyDTO.getProductId() == null) return new ResponseEntity<>("missing productId", HttpStatus.FORBIDDEN);

        if(favoriteApplyDTO.getImgUrl() == null) return new ResponseEntity<>("missing imgUrl", HttpStatus.FORBIDDEN);

        Optional<Client> client = clientService.findByEmail(authentication.getName());

        if(client.get().getFavorites().stream().anyMatch(favorite -> favorite.getProductId().equals(favoriteApplyDTO.getProductId()))){

            return new ResponseEntity<>("the product already exists in favorites", HttpStatus.FORBIDDEN);

        }

        try {

            favoriteService.add(client.get(), favoriteApplyDTO);
            return new ResponseEntity<>("product added to favorite successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @DeleteMapping("/client/current/favorites/{id}")
    public ResponseEntity<?> delete(Authentication authentication, @PathVariable Long id){

        Optional<Client> client = clientService.findByEmail(authentication.getName());


        if(client.get().getFavorites().stream().noneMatch(favorite -> favorite.getId().equals(id))){

            return new ResponseEntity<>("favorite not found", HttpStatus.FORBIDDEN);

        }

        favoriteService.delete(id);

        return new ResponseEntity<>("Favorite deleted succesfully", HttpStatus.OK);

    }

}
