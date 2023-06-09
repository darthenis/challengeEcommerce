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

    private Set<PurchaseOrderDTO> ticket ;

    private boolean isEnabled;


    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.lastName = client.getLastName();
        this.tel = client.getTel();
        this.email = client.getEmail();
        this.password = client.getPassword();
        this.urlImg = client.getUrlImg();
        this.favorites = client.getFavorites().stream().map(FavoriteDTO::new).collect(Collectors.toSet());
        this.ticket = client.getTickets().stream().map(PurchaseOrderDTO::new).collect(Collectors.toSet());
        this.isEnabled = client.isEnabled();
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

    public Set<PurchaseOrderDTO> getTickets() {
        return ticket;
    }

    public boolean getIsEnabled() {
        return isEnabled;
    }
}
