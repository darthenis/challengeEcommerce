package com.easybuy.easybuy.DTO;

import com.easybuy.easybuy.models.CategoriesEnum;

import java.time.LocalDate;
import java.util.List;

public class UpdateProductDTO {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private int discount;
    private int Stock;
    private LocalDate date;

    private List<CategoriesEnum> categories;



    public UpdateProductDTO(Long id,String name, String description, Double price, int discount, int stock, List<CategoriesEnum> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.Stock = stock;
        this.categories=categories;
    }

    public List<CategoriesEnum> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesEnum> categories) {
        this.categories = categories;
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
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
