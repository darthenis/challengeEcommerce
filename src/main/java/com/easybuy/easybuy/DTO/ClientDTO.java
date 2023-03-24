package com.easybuy.easybuy.DTO;

import com.easybuy.easybuy.models.Client;

import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {

    private Long id;

    private String name;

    private String lastName;

    private String tel;

    private String email;

    private String password;

    private String urlImg;

    private Set<FavoriteDTO> favorites;

    private Set<TicketDTO> orders ;

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.lastName = client.getLastName();
        this.tel = client.getTel();
        this.email = client.getEmail();
        this.password = client.getPassword();
        this.urlImg = client.getUrlImg();
        this.favorites = client.getFavorites().stream().map(favorite -> new FavoriteDTO(favorite) ).collect(Collectors.toSet());
        this.orders = client.getOrders().stream().map(order -> new TicketDTO(order)).collect(Collectors.toSet());
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

    public Set<FavoriteDTO> getFavorites() {
        return favorites;
    }

    public Set<TicketDTO> getOrders() {
        return orders;
    }
}
