package com.example.company.API.model;

/**
 * Created by Sebastian Paciorek
 */
public class AuthUser {

    private String login;
    private String password;

    public AuthUser() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
