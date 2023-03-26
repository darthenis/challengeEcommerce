package com.easybuy.easybuy.services.impl;

import com.easybuy.easybuy.DTO.NewClientDTO;
import com.easybuy.easybuy.models.Client;
import com.easybuy.easybuy.repositories.ClientRepository;
import com.easybuy.easybuy.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Lazy
    @Autowired
    PasswordEncoder passwordEncoder;

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

        if(newClientDTO.getName() == null || newClientDTO.getName().isEmpty()) throw new Exception("missing name");

        if(newClientDTO.getLastName() == null || newClientDTO.getLastName().isEmpty()) throw new Exception("missing lastName");

        if(newClientDTO.getEmail() == null || newClientDTO.getEmail().isEmpty()) throw new Exception("missing email");

        if(newClientDTO.getPassword() == null || newClientDTO.getPassword().isEmpty()) throw new Exception("missing password");

        if(newClientDTO.getTel() == null || newClientDTO.getTel().isEmpty()) throw new Exception("missing tel");

        if(clientRepository.existsByEmail(newClientDTO.getEmail())) throw new Exception("Email already registered");

        Client newClient = new Client(newClientDTO.getName(), newClientDTO.getLastName(), newClientDTO.getTel(), newClientDTO.getEmail(), passwordEncoder.encode(newClientDTO.getPassword()));

        clientRepository.save(newClient);
    }

    @Override
    public void editClient(NewClientDTO newClientDTO, String email) throws Exception {

        if (    (newClientDTO.getName() == null || newClientDTO.getName().isEmpty()) &&
                (newClientDTO.getLastName() == null || newClientDTO.getLastName().isEmpty()) &&
                (newClientDTO.getEmail() == null || newClientDTO.getEmail().isEmpty()) &&
                (newClientDTO.getTel() == null || newClientDTO.getTel().isEmpty()))

            throw new Exception("missing value");

        Optional<Client> client = clientRepository.findByEmail(email);

        if (newClientDTO.getName() != null && !newClientDTO.getName().isEmpty()) client.get().setName(newClientDTO.getName());

        if (newClientDTO.getLastName() != null && !newClientDTO.getLastName().isEmpty()) client.get().setLastName(newClientDTO.getLastName());

        if (newClientDTO.getEmail() != null && !newClientDTO.getEmail().isEmpty()) client.get().setEmail(newClientDTO.getEmail());

        if (newClientDTO.getTel() != null && !newClientDTO.getTel().isEmpty()) client.get().setTel(newClientDTO.getTel());

        clientRepository.save(client.get());

        if (newClientDTO.getEmail() != null) {

            Authentication auth = new UsernamePasswordAuthenticationToken(newClientDTO.getEmail(), client.get().getPassword(), AuthorityUtils.createAuthorityList("CLIENT"));

            SecurityContextHolder.getContext().setAuthentication(auth);

        }

    }

    @Override
    public Optional<Client> findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    @Override
    public void editClientPassword(Authentication authentication, String oldPassword, String newPassword) throws Exception {
        Client selectClient = clientRepository.findByEmail(authentication.getName()).orElse(null);

        if (selectClient == null) {
            throw new Exception("Missing client");
        }
        if (oldPassword.isEmpty()) {
            throw new Exception("Missing old password");
        }
        if (newPassword.isEmpty()) {
            throw new Exception("missing new password");
        }
        if (passwordEncoder.matches(selectClient.getPassword(), oldPassword)) {
            selectClient.setPassword(passwordEncoder.encode(newPassword));
            clientRepository.save(selectClient);
        } else {
            throw new Exception("Wrong password");
        }
    }

}
