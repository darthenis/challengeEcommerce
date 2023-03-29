package com.easybuy.easybuy.repositories;

import com.easybuy.easybuy.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository <Product, Long> {


    @Query("SELECT p FROM Product p ORDER BY p.date DESC")
    List<Product> findTop4ByDate();
}
