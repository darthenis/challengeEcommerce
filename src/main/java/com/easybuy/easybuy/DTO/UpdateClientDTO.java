package com.easybuy.easybuy.DTO;

public class UpdateClientDTO {
    private String name;
    private String lastName;
    private String tel;
    private String email;


    public UpdateClientDTO(String name, String lastName, String tel, String email) {
        this.name = name;
        this.lastName = lastName;
        this.tel = tel;
        this.email = email;

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


}
