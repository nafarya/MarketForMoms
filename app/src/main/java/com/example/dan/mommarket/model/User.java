package com.example.dan.mommarket.model;

/**
 * Created by dan on 18.08.16.
 */

public class User {
    private String name;
    private String login;

    public User(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
