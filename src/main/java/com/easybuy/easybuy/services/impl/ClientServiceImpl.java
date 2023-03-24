package com.easybuy.easybuy.services.impl;

import com.easybuy.easybuy.DTO.NewClientDTO;
import com.easybuy.easybuy.models.Client;
import com.easybuy.easybuy.repositories.ClientRepository;
import com.easybuy.easybuy.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public void editClient(NewClientDTO newClientDTO, Authentication authentication) throws Exception {

        if(newClientDTO.getName().isEmpty() && newClientDTO.getLastName().isEmpty() && newClientDTO.getEmail().isEmpty() && newClientDTO.getTel().isEmpty()) throw new Exception("missing value");

        Optional<Client> client = clientRepository.findByEmail(authentication.getName());

        if(!newClientDTO.getName().isEmpty()) client.get().setName(newClientDTO.getName());

        if(!newClientDTO.getLastName().isEmpty()) client.get().setLastName(newClientDTO.getLastName());

        if(!newClientDTO.getEmail().isEmpty()) client.get().setEmail(newClientDTO.getEmail());

        if(!newClientDTO.getTel().isEmpty()) client.get().setTel(newClientDTO.getTel());

        clientRepository.save(client.get());

        authentication.setAuthenticated(false);

        if(!newClientDTO.getEmail().isEmpty()){

            Authentication auth = new UsernamePasswordAuthenticationToken(newClientDTO.getEmail(), client.get().getPassword(), AuthorityUtils.createAuthorityList("CLIENT"));

            SecurityContextHolder.getContext().setAuthentication(auth);

        }


    }

    @Override
    public Optional<Client> findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }
}
