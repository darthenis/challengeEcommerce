package com.easybuy.easybuy.models;

import com.easybuy.easybuy.DTO.FavoriteApplyDTO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String name;

    @Column(name = "imgurl", length = 2000)
    private String ImgUrl;

    private Double price;

    private Long productId;

    private int stock;
    @Column(name = "description", length = 2000)
    private String description;

    @ManyToOne
    private Client client;

    public Favorite(){}

    public Favorite(String name, String imgUrl, Double price, Long productId, Client client, String description) {
        this.name = name;
        ImgUrl = imgUrl;
        this.price = price;
        this.productId = productId;
        this.client = client;
        this.description = description;
    }

    public Favorite(FavoriteApplyDTO favoriteApplyDTO){
        this.name = favoriteApplyDTO.getName();
        ImgUrl = favoriteApplyDTO.getImgUrl();
        this.price = favoriteApplyDTO.getPrice();
        this.productId = favoriteApplyDTO.getProductId();
        this.stock = favoriteApplyDTO.getStock();
        this.description = favoriteApplyDTO.getDescription();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
