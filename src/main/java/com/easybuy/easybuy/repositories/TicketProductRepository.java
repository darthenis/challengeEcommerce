package com.easybuy.easybuy.repositories;

import com.easybuy.easybuy.models.TicketProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TicketProductRepository extends JpaRepository <TicketProduct,Long> {

}