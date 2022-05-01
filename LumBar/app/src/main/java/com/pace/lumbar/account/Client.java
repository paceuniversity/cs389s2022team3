package com.pace.lumbar.account;

import java.io.Serializable;

public class Client implements Serializable { //parent class for Client and Lawyer

    private String name;
    private String phone;
    private String email;
    private String city;
    private String state;
    private String username;
    private String password;
    private String caseType;
    private String caseDetails;
    private String address;

    //TODO: profile pic

    public Client() {
    }

    public Client(String name, String phone, String email, String city, String state, String address,
                  String password, String caseType, String caseDetails) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.city = city;
        this.state = state;
        this.address = address;
        this.password = password;
        this.email = email;
        this.caseType = caseType;
        this.caseDetails = caseDetails;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phone;
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

    public String getEmail() {
        return email;
    }
    //for testing
    public String getPassword() {
        return password;
    }

//    public Case getCase(){
//        return myCase;
//    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phone = phoneNumber;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setName(String name) {
        this.name = this.name;
    }

    public String getCaseType() {
        return caseType;
    }

    public String getCaseDetails() {
        return caseDetails;
    }

//    public void setMyCase(Case myCase) {
//        this.myCase = myCase;
//    }
}




