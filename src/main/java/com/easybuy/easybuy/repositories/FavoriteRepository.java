package com.easybuy.easybuy.repositories;

import com.easybuy.easybuy.models.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface FavoriteRepository extends JpaRepository <Favorite, Long>{

}
