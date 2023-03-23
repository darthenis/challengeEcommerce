package com.easybuy.easybuy.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String name;

    private String description;

    private Double price;

    private int discount;

    @ElementCollection
    private List<String> imgsUrls;

    private int stock;

    private LocalDate date;

    @ElementCollection
    private List<CategoriesEnum> categoriesEnums;

    public Product(){}

    public Product(String name, String description, Double price, int discount, List<String> imgsUrls, int stock, LocalDate date) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.imgsUrls = imgsUrls;
        this.stock = stock;
        this.date = date;
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

    public List<String> getImgsUrls() {
        return imgsUrls;
    }

    public void setImgsUrls(List<String> imgsUrls) {
        this.imgsUrls = imgsUrls;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
