package com.easybuy.easybuy.services;

import com.easybuy.easybuy.DTO.FavoriteApplyDTO;
import com.easybuy.easybuy.models.Client;
import com.easybuy.easybuy.models.Favorite;

public interface FavoriteService {

    public void save(Favorite favorite);

    public void delete(Long id);

    public void add(Client client, FavoriteApplyDTO favoriteApplyDTO) throws Exception;
}
