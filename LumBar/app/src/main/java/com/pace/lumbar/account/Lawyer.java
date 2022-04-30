package com.pace.lumbar.account;

import android.net.Uri;

import java.io.Serializable;

public class Lawyer implements Serializable {
    private String name;
    private String email;
    private String phone;
    private String password;
    private String profileIMGUri;

    private String firmName;
    private String address;
    private String city;
    private String state;
    private String firmEmail;
    private String firmPhone;
    private String website;
    private String topic;
    private String budget;

    public Lawyer(String name, String email, String phone, String password, String imgUri,
                  String firmName, String address,String city,
                  String state, String emailAddress, String phoneNumber,
                  String website, String firmTopic, String budget) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.profileIMGUri = imgUri;
        this.firmName = firmName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.firmEmail = emailAddress;
        this.firmPhone = phoneNumber;
        this.website = website;
        this.topic = firmTopic;
        this.budget = budget;
    }

    public String getWebsite() {
        return website;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getBudget() {
        return budget;
    }

    public String getFirmEmail() {
        return firmEmail;
    }

    public String getFirmName() {
        return firmName;
    }

    public String getFirmPhone() {
        return firmPhone;
    }

    public String getTopic() {
        return topic;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public void setFirmEmail(String firmEmail) {
        this.firmEmail = firmEmail;
    }

    public void setFirmPhone(String firmPhone) {
        this.firmPhone = firmPhone;
    }

    public void setTopic(String firmTopic) {
        this.topic = firmTopic;
    }

    public String getProfileIMGUri() {
        return profileIMGUri;
    }

    public void setProfileIMGUri(String imgURI) {
        this.profileIMGUri = imgURI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
