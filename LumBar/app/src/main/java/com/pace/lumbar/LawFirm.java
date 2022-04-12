package com.pace.lumbar;

public class LawFirm {

    private String firmName;
    private String address;
    private String city;
    private String state;
    private String emailAddress;
    private String phoneNumber;
    private String website;
    private String firmTopic;
    private String caseWebsite;

    public LawFirm(String firmName, String address,String city,
                   String state, String emailAddress, String phoneNumber,
                   String website, String firmTopic, String caseWebsite) {
        this.firmName = firmName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.firmTopic = firmTopic;
        this.caseWebsite = caseWebsite;
    }

    public String getFirmName() {
        return firmName;
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
        return firmTopic;
    }

    public String getCaseWebsite() {
        return caseWebsite;
    }
}
