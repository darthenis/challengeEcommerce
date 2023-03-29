package com.easybuy.easybuy.DTO;

import com.easybuy.easybuy.models.Favorite;

public class FavoriteDTO {

    private Long id;

    private String name;

    private String imgUrl;

    private int stock;

    private Double price;

    private String description;

    public FavoriteDTO(Favorite favorite) {
        this.id = favorite.getId();
        this.name = favorite.getName();
        this.imgUrl = favorite.getImgUrl();
        this.price = favorite.getPrice();
        this.stock = favorite.getStock();
        this.description = favorite.getDescription();
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

    public int getStock() {
        return stock;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
