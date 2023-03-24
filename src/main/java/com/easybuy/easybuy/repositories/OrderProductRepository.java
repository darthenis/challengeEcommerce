package com.easybuy.easybuy.repositories;

import com.easybuy.easybuy.models.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
<<<<<<< HEAD:src/main/java/com/easybuy/easybuy/repositories/OrderProduct.java
public interface OrderProduct extends JpaRepository <OrderProduct, Long> {
=======
public interface OrderProductRepository extends JpaRepository <OrderProduct,Long> {
>>>>>>> OrderProductService:src/main/java/com/easybuy/easybuy/repositories/OrderProductRepository.java
}
