package com.example.sns;

import java.time.LocalDate;
import java.util.List;

import static java.time.LocalTime.now;

public class User {
    private String login;
    private String password;
    private String data;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getData() {
        return data;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Users{" +
                "\"login\":\"'" + login + "\"" +
                ", \"password\":\"" + password + "\"" +
                ", \"data\":\"" + data + "\"" +
                "}";
    }
}
