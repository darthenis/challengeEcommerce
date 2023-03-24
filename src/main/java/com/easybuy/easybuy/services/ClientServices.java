package com.easybuy.easybuy.services;

import com.easybuy.easybuy.models.Client;

import java.util.List;

public interface ClientServices {

    public void save(Client client);

    public List<Client> findAll();

}
