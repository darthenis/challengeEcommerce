package com.easybuy.easybuy.repositories;

import com.easybuy.easybuy.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Ticket,Long> {
}
