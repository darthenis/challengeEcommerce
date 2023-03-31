package com.easybuy.easybuy.DTO;

import com.easybuy.easybuy.models.CategoriesEnum;
import com.easybuy.easybuy.models.Product;

import javax.persistence.ElementCollection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDTO {
    private Long id;

    private String name;

    private String description;

    private Double price;

    private int discount;


    private List<String> imgsUrls;

    private int stock;

    private LocalDate date;

    private List<CategoriesEnum> categoriesEnums;

    private List<RateDTO> rates = new ArrayList<>();

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.discount = product.getDiscount();
        this.imgsUrls = product.getImgsUrls();
        this.stock = product.getStock();
        this.date = product.getDate();
        this.categoriesEnums = product.getCategoriesEnums();
        this.rates = product.getRates().stream().map(rate-> new RateDTO(rate)).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }

    public List<String> getImgsUrls() {
        return imgsUrls;
    }

    public int getStock() {
        return stock;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<CategoriesEnum> getCategoriesEnums() {
        return categoriesEnums;
    }

    public List<RateDTO> getRates() {
        return rates;
    }
}
