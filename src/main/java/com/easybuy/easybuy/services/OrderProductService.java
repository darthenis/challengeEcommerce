package com.easybuy.easybuy.services;

import com.easybuy.easybuy.models.OrderProduct;
import org.hibernate.mapping.Set;

import java.util.List;

public interface OrderProductService {
    void save (OrderProduct orderProduct);

    List<OrderProduct> findAll() ;


}
