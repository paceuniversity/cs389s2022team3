package com.pace.lumbar;

public class Lawyer {
    private String name;
    private String email;
    private int phone;
    private String username;
    private String password;
    //TODO: profile pic

    public Lawyer(String name, String email, int phone, String username,
                  String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    //for testing
    public String getPassword() {
        return password;
    }
}
