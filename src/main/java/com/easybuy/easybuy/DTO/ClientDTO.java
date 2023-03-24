package com.easybuy.easybuy.DTO;

import com.easybuy.easybuy.models.Client;
import com.easybuy.easybuy.models.Favorite;
import com.easybuy.easybuy.models.Order;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class ClientDTO {

    private Long id;

    private String name;

    private String lastName;

    private String tel;

    private String email;

    private String password;

    private String urlImg;

    private List<FavoriteDTO> favorites;

    private Set<OrderDTO> orders ;

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.lastName = client.getLastName();
        this.tel = client.getTel();
        this.email = client.getEmail();
        this.password = client.getPassword();
        this.urlImg = client.getUrlImg();
        this.favorites = client.getFavorites().stream().map(favorite -> new FavoriteDTO(favorite) ).collect(Collectors.toList());
        this.orders = client.getOrders().stream().map(order -> new OrderDTO(order)).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public Set<Order> getOrders() {
        return orders;
    }
}
