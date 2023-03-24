package com.easybuy.easybuy.services.impl;

import com.easybuy.easybuy.models.Rate;
import com.easybuy.easybuy.repositories.RateRepository;
import com.easybuy.easybuy.services.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateServiceImpl implements RateService {

    @Autowired
    RateRepository rateRepository;


    @Override
    public void save(Rate rate) {
        rateRepository.save(rate)
    }

    @Override
    public List<Rate> findAll() {
        return rateRepository.findAll();
    }
}
