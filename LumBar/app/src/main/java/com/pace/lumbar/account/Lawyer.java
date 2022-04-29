package com.pace.lumbar.account;

import java.io.Serializable;

public class Lawyer implements Serializable {
    private String name;
    private String email;
    private String phone;
    private String password;
    private LawFirm firm;
    private String imgURI;
    //TODO: profile pic

    public Lawyer(String name, String email, String phone, String password, String imgUri) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.imgURI = imgUri;
    }

    public String getImgURI() {
        return imgURI;
    }

    public void setImgURI(String imgURI) {
        this.imgURI = imgURI;
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

    //for testing
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirm(LawFirm firm){
        this.firm = firm;
    }

    public LawFirm getFirm() {return firm;}
}
