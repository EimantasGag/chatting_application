package com.eimantasgag.learning_springboot.model;

public class LoginData {
    private String username;
    private String password;
    private boolean rememberUser;

    LoginData(){}

    public LoginData(String username, String password, boolean rememberUser) {
        this.username = username;
        this.password = password;
        this.rememberUser = rememberUser;
    }

    public boolean isRememberUser() {
        return rememberUser;
    }
    public void setRememberUser(boolean rememberUser) {
        this.rememberUser = rememberUser;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
