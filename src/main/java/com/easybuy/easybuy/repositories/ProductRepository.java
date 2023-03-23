package com.easybuy.easybuy.repositories;

import com.easybuy.easybuy.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository <Product, Long> {
}
