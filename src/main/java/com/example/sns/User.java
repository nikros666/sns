package com.example.sns;

public class User {
    private String login;
    private String password;
    private String dat;
    private String email;

    public String getEmail() {
        return email;
    }

    public String getLogin() { return login; }

    public String getPassword() {
        return password;
    }

    public String getDat() {
        return dat;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setData(String dat) {
        this.dat = dat;
    }
    public User(String login, String password, String dat, String email) {
        this.login = login;
        this.password = password;
        this.dat = dat;
        this.email = email;
    }
    @Override
    public String toString() {
        return "Users{" +
                "\"login\":\"'" + login + "\"" +
                ", \"password\":\"" + password + "\"" +
                ", \"data\":\"" + dat + "\"" +
                ", \"email\":\"" + email + "\"" +
                "}";
    }
}
