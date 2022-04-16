package com.pace.lumbar;

import java.io.Serializable;

public class Client implements Serializable { //parent class for Client and Lawyer

    private String realName;
    private String phoneNumber;
    private String city;
    private String state;
    private String username;
    private String password;
    //TODO: profile pic

    public Client(){}

    public Client(String realName, String phoneNumber, String city, String state, String username,
                  String password) {
        this.realName = realName;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.state = state;
        this.username = username;
        this.password = password;
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

    //for testing
    public String getPassword() {
        return password;
    }
}
