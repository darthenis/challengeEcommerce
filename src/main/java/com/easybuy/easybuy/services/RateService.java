package com.easybuy.easybuy.services;

import com.easybuy.easybuy.models.Client;
import com.easybuy.easybuy.models.Rate;

import java.util.List;

public interface RateService {
    public void save(Rate rate);

    public List<Rate> findAll();
}
