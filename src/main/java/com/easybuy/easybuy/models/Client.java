package com.easybuy.easybuy.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String name;

    private String lastName;

    private String tel;

    private String email;

    private String password;

    private String urlImg;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Favorite> favorites;

    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    private Set<Ticket> tickets = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Rate> rates;


    public Client(){}
    public Client(String name, String lastName, String tel, String email, String password){
        this.name = name;
        this.lastName = lastName;
        this.tel = tel;
        this.email = email;
        this.password = password;
    }


    public void addFavorites(Favorite favorite){
        favorite.setClient(this);
        favorites.add(favorite);
    }
    public void addTicket(Ticket ticket){
        ticket.setClient(this);
        tickets.add(ticket);
    }

    public void addRate(Rate rate){

        rate.setClient(this);
        rates.add(rate);

    }

    public void setFavorites(Favorite favorite) {
        this.favorites.add(favorite);
    }

    public Set<Favorite> getFavorites() {
        return favorites;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }
}
