package com.pace.lumbar.account;

import java.io.Serializable;

public class Client implements Serializable { //parent class for Client and Lawyer

    private String uid;
    private String name;
    private String phone;
    private String email;
    private String city;
    private String state;
    private String address;
    private String password;
    private String topic;
    private String detail;
    private String profImageUri;

    public Client(String uid, String name, String phoneNumber, String email, String address, String city, String state,
                  String password, String caseType, String caseDetails, String profImageUri) {
        this.uid = uid;
        this.name = name;
        this.phone = phoneNumber;
        this.email = email;
        this.city = city;
        this.state = state;
        this.address = address;
        this.password = password;
        this.email = email;
        this.topic = caseType;
        this.detail = caseDetails;
        this.profImageUri = profImageUri;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getProfImageUri() {
        return profImageUri;
    }

    public void setProfImageUri(String profImageUri) {
        this.profImageUri = profImageUri;
    }

    public String getName() {
        return name;
    }
  
    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setPhone(String phoneNumber) {
        this.phone = phoneNumber;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setName(String realName) {
        this.name = realName;
    }

    public String getTopic() {
        return topic;
    }

    public String getDetail() {
        return detail;
    }
}




