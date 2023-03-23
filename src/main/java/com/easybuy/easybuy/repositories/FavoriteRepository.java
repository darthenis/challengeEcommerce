package com.easybuy.easybuy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface FavoriteRepository extends JpaRepository <FavoriteRepository, Long>{

}
