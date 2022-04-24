package com.pace.lumbar.account;

import java.io.Serializable;

public class Client implements Serializable { //parent class for Client and Lawyer

    private String realName;
    private String phoneNumber;
    private String email;
    private String city;
    private String state;
    private String username;
    private String password;
    private String caseType;
    private String caseDetails;

    //TODO: profile pic

    public Client() {
    }

    public Client(String realName, String phoneNumber, String email, String city, String state, String username,
                  String password, String caseType, String caseDetails) {
        this.realName = realName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.city = city;
        this.state = state;
        this.username = username;
        this.password = password;
        this.email = email;
        this.caseType = caseType;
        this.caseDetails = caseDetails;
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
        this.phoneNumber = phoneNumber;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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




