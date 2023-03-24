package com.easybuy.easybuy.services.impl;

import com.easybuy.easybuy.models.Client;
import com.easybuy.easybuy.repositories.ClientRepository;
import com.easybuy.easybuy.services.ClientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientServices {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public void save(Client client) {
        clientRepository.save(client);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
}
