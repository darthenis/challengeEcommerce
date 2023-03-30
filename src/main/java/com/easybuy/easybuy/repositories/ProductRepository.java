package com.easybuy.easybuy.repositories;

import com.easybuy.easybuy.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository <Product, Long> {

    List<Product> findTop4ByOrderByDateDesc();

    List<Product> findTop4ByOrderByDiscountDesc();
}
