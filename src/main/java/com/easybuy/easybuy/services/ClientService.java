package com.easybuy.easybuy.services;

import com.easybuy.easybuy.DTO.NewClientDTO;
import com.easybuy.easybuy.models.Client;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    public void save(Client client);

    public List<Client> findAll();

    public void createClient(NewClientDTO newClientDTO) throws Exception;

    public void editClient(NewClientDTO newClientDTO, String email) throws Exception;

    public Optional<Client> findByEmail(String email);

    void editClientPassword(Authentication authentication, String oldPassword, String newPassword) throws Exception;

    void activeClient(String keyToken) throws Exception;

    void resendEmail(String email) throws Exception;
}
