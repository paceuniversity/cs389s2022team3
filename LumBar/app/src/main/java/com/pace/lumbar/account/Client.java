package com.pace.lumbar.account;

import java.io.Serializable;

public class Client implements Serializable { //parent class for Client and Lawyer

    private String name;
    private String phone;
    private String email;
    private String city;
    private String state;
    private String address;
    private String password;
    private String topic;
    private String detail;
    private String profileIMGUri;

    //TODO: profile pic

    public Client() {
    }

    public Client(String realName, String phoneNumber, String email, String address, String city, String state,
                  String password, String caseType, String caseDetails, String profile) {
        this.name = realName;
        this.phone = phoneNumber;
        this.email = email;
        this.city = city;
        this.state = state;
        this.address = address;
        this.password = password;
        this.email = email;
        this.topic = caseType;
        this.detail = caseDetails;
        this.profileIMGUri = profile;
    }

    public String getProfileIMGUri() {
        return profileIMGUri;
    }

    public String getTopic() {
        return topic;
    }

    public String getDetail() {
        return detail;
    }

    public void setProfileIMGUri(String profileIMGUri) {
        this.profileIMGUri = profileIMGUri;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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

    public void setRealName(String realName) {
        this.name = realName;
    }

    public String getCaseType() {
        return topic;
    }

    public String getCaseDetails() {
        return detail;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCaseType(String caseType) {
        this.topic = caseType;
    }

    public void setCaseDetails(String caseDetails) {
        this.detail = caseDetails;
    }
}