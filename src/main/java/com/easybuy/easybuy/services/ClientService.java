package com.easybuy.easybuy.services;

import com.easybuy.easybuy.DTO.NewClientDTO;
import com.easybuy.easybuy.models.Client;

import java.util.List;

public interface ClientService {

    public void save(Client client);

    public List<Client> findAll();

    public void createClient(NewClientDTO newClientDTO) throws Exception;

}
