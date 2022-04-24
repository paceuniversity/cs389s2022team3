package com.pace.lumbar.account;

public class Account {
    private String username;
    private String password;
    //TODO: profile pic

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
