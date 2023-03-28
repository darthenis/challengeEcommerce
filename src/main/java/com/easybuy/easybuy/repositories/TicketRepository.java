package com.easybuy.easybuy.repositories;

import com.easybuy.easybuy.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query(value = "SELECT MAX(id) FROM PurchaseOrder")
    public Long findMaxId();
}
