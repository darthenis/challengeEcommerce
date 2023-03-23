package com.easybuy.easybuy.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

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
    private List<Favorite> favorites;

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
}
