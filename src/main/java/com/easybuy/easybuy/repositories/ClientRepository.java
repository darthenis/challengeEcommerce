package com.easybuy.easybuy.repositories;

import com.easybuy.easybuy.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import java.util.Optional;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client,Long> {

    Optional<Client> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<Client> findByKeyToken(String keyToken);

}
