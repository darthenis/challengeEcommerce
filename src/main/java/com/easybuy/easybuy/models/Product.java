package com.easybuy.easybuy.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

    private Boolean status = true;

    @ElementCollection
    private List<CategoriesEnum> categoriesEnums;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<Rate> rates;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private Set<TicketProduct> ticketProducts;


    public Product(){}

    public Product(String name, String description, Double price, int discount, int stock, LocalDate date,List<CategoriesEnum> categoriesEnums) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.imgsUrls = imgsUrls;
        this.stock = stock;
        this.date = date;
        this.categoriesEnums = categoriesEnums;

    }

    public void addRate(Rate rate) {

        rate.setProduct(this);
        rates.add(rate);

    }

    public void addTicketProduct(TicketProduct ticketProduct){

        ticketProduct.setProduct(this);


    }


    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Set<TicketProduct> getTicketProducts() {
        return ticketProducts;
    }

    public void setTicketProducts(Set<TicketProduct> ticketProducts) {
        this.ticketProducts = ticketProducts;
    }

    public Set<TicketProduct> getTicketProduct() {
        return ticketProducts;
    }

    public void setTicketProduct(TicketProduct ticketProduct) {
        this.ticketProducts.add(ticketProduct);
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

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    public List<CategoriesEnum> getCategoriesEnums() {
        return categoriesEnums;
    }

    public void setCategoriesEnums(List<CategoriesEnum> categoriesEnums) {
        this.categoriesEnums = categoriesEnums;
    }
}
