package com.easybuy.easybuy.services.impl;

import com.easybuy.easybuy.models.Favorite;
import com.easybuy.easybuy.repositories.FavoriteRepository;
import com.easybuy.easybuy.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    FavoriteRepository favoriteRepository;

    @Override
    public void save(Favorite favorite) {

        favoriteRepository.save(favorite);
    }
}
