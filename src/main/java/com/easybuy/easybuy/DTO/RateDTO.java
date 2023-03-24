package com.easybuy.easybuy.DTO;

import com.easybuy.easybuy.models.Product;
import com.easybuy.easybuy.models.Rate;
import com.easybuy.easybuy.models.StarsEnum;

public class RateDTO {

    private Long id;

    private String commentary;

    private StarsEnum stars;

    private Product product;

    public RateDTO(Rate rate){
        this.id = rate.getId();
        this.commentary = rate.getCommentary();
        this.stars = rate.getStars();
        this.product = rate.getProduct();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public StarsEnum getStars() {
        return stars;
    }

    public void setStars(StarsEnum stars) {
        this.stars = stars;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
