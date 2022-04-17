package com.pace.lumbar;

import java.io.Serializable;

public class Client implements Serializable { //parent class for Client and Lawyer

    private String realName;
    private String phoneNumber;
    private String city;
    private String state;
    private String email;
    private String username;
    private String password;
    //TODO: profile pic
  
    public Client(String realName, String phoneNumber, String city, String state, String username,
                  String password, String email) {
        this.realName = realName;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.state = state;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getRealName() {
        return realName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() { return email; }

    //for testing
    public String getPassword() {
        return password;
    }
}
