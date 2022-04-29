package com.pace.lumbar.account;

public class LawFirm {

    private String firmName;
    private String address;
    private String city;
    private String state;
    private String emailAddress;
    private String phoneNumber;
    private String website;
    private String topic;
    private String budget;

    public LawFirm(String firmName, String address,String city,
                   String state, String emailAddress, String phoneNumber,
                   String website, String firmTopic, String budget) {
        this.firmName = firmName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.topic = firmTopic;
        this.budget = budget;
    }

    public String getTopic() {
        return topic;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getWebsite() {
        return website;
    }

    public String getFirmTopic() {
        return topic;
    }
}
