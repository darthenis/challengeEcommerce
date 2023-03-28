package com.easybuy.easybuy.repositories;

import com.easybuy.easybuy.models.PurchaseOrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PurchaseOrderProductRepository extends JpaRepository <PurchaseOrderProduct,Long> {

}
