package com.easybuy.easybuy.DTO;

import com.easybuy.easybuy.models.Favorite;

public class FavoriteDTO {

    private Long id;

    private String name;

    private String imgUrl;

    public FavoriteDTO(Favorite favorite) {
        this.id = favorite.getId();
        this.name = favorite.getName();
        this.imgUrl = favorite.getImgUrl();
    }

    public Long getId() {
        return id;
    }



    public String getName() {
        return name;
    }



    public String getImgUrl() {
        return imgUrl;
    }

}
