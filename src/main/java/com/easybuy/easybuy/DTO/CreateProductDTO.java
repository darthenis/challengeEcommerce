package com.easybuy.easybuy.DTO;

import com.easybuy.easybuy.models.CategoriesEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CreateProductDTO {

    private String name;
    private String description;
    private Double price;
    private int discount;
    private int stock;
    private LocalDateTime date;
    private List<CategoriesEnum> categories;


        public CreateProductDTO(String name, String description, Double price, int discount, int stock, LocalDateTime date,List<CategoriesEnum> categories) {
            this.name = name;
            this.description = description;
            this.price = price;
            this.discount = discount;
            this.stock = stock;
            this.date = date;
            this.categories=categories;
     }

    public List<CategoriesEnum> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesEnum> categories) {
        this.categories = categories;
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

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
