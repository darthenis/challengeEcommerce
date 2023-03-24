package com.easybuy.easybuy.services.impl;

import com.easybuy.easybuy.DTO.NewClientDTO;
import com.easybuy.easybuy.models.Client;
import com.easybuy.easybuy.repositories.ClientRepository;
import com.easybuy.easybuy.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

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

    @Override
    public void createClient(NewClientDTO newClientDTO) throws Exception {

        if(newClientDTO.getName().isEmpty()) throw new Exception("missing name");

        if(newClientDTO.getLastName().isEmpty()) throw new Exception("missing lastName");

        if(newClientDTO.getEmail().isEmpty()) throw new Exception("missing email");

        if(newClientDTO.getPassword().isEmpty()) throw new Exception("missing password");

        if(newClientDTO.getTel().isEmpty()) throw new Exception("missing tel");

        Client newClient = new Client(newClientDTO.getName(), newClientDTO.getLastName(), newClientDTO.getTel(), newClientDTO.getEmail(), newClientDTO.getPassword());

        clientRepository.save(newClient);
    }
}
