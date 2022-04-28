package com.pace.lumbar.match;

public class Cards {
    private String name, lawFirm, email, phone, address, topic, website, profileIMGUri;

    public Cards(String name, String lawFirm, String email, String phone, String address, String topic, String website, String profileIMGUri){
        this.email = email;
        this.name = name;
        this.lawFirm = lawFirm;
        this.phone = phone;
        this.address = address;
        this.topic = topic;
        this.website = website;
        this.profileIMGUri = profileIMGUri;
    }

    public String getProfileIMGUri() {
        return profileIMGUri;
    }

    public void setProfileIMGUri(String profileIMGUri) {
        this.profileIMGUri = profileIMGUri;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getWebsite() {
        return website;
    }

    public String getLawFirm() {
        return lawFirm;
    }

    public void setLawFirm(String lawFirm) {
        this.lawFirm = lawFirm;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public String getEmail(){
        return email;
    }

    public String setEmail(String email){
        return this.email = email;
    }

    public String getName(){
        return name;
    }

    public String setName(String name){
        return this.name = name;
    }
}
