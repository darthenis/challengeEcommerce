package com.easybuy.easybuy.DTO;

import com.easybuy.easybuy.models.Product;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class PurchaseDTO {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private List<String> imgsUrls;

    private LocalDateTime date;

    private boolean rated = false;

    public PurchaseDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imgsUrls = product.getImgsUrls();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<String> getImgsUrls() {
        return imgsUrls;
    }

    public void setImgsUrls(List<String> imgsUrls) {
        this.imgsUrls = imgsUrls;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isRated() {
        return rated;
    }

    public void setRated(boolean rated) {
        this.rated = rated;
    }
}
