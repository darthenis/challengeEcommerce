package com.easybuy.easybuy.DTO;

public class NewClientDTO {

    private String name;
    private String lastName;
    private String tel;
    private String email;
    private String password;

    public NewClientDTO(String name, String lastName, String tel, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.tel = tel;
        this.email = email;
        this.password = password;
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
}
