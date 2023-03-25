package com.easybuy.easybuy.services;

import com.easybuy.easybuy.models.Client;
import com.easybuy.easybuy.models.Rate;
import com.easybuy.easybuy.models.StarsEnum;

import java.util.List;

public interface RateService {
    public void save(Rate rate);

    public List<Rate> findAll();

    public void addRate(Long productId, String commentary, StarsEnum starsEnum, Client client) throws Exception;
}
