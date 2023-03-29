package com.easybuy.easybuy.services.impl;

import com.easybuy.easybuy.DTO.FavoriteApplyDTO;
import com.easybuy.easybuy.models.Client;
import com.easybuy.easybuy.models.Favorite;
import com.easybuy.easybuy.repositories.FavoriteRepository;
import com.easybuy.easybuy.services.ClientService;
import com.easybuy.easybuy.services.FavoriteService;
import com.easybuy.easybuy.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    FavoriteRepository favoriteRepository;

    @Autowired
    ClientService clientService;

    @Autowired
    ProductService productService;

    @Override
    public void save(Favorite favorite) {

        favoriteRepository.save(favorite);
    }

    @Override
    public void delete(Long id) {
        favoriteRepository.deleteById(id);
    }

    @Override
    public void add(Client client, FavoriteApplyDTO favoriteApplyDTO) throws Exception {

        if(!productService.productExist(favoriteApplyDTO.getProductId())) throw new Exception("product not found");

        Favorite favorite = new Favorite(favoriteApplyDTO);

        favorite.setClient(client);

        favoriteRepository.save(favorite);

    }

}
