package com.easybuy.easybuy.repositories;

import com.easybuy.easybuy.models.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RateRepository extends JpaRepository<Rate,Long> {
}
