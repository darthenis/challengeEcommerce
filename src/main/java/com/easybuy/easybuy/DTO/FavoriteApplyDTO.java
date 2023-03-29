package com.easybuy.easybuy.DTO;

public class FavoriteApplyDTO {

    private String name;

    private String ImgUrl;

    private Double price;

    private Long productId;

    private String description;

    private int stock;

    public FavoriteApplyDTO(String name, String imgUrl, Double price, Long productId, int stock, String description) {
        this.name = name;
        ImgUrl = imgUrl;
        this.price = price;
        this.productId = productId;
        this.stock = stock;
        this.description = description;
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
