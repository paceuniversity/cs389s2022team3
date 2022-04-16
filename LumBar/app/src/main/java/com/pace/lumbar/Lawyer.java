package com.pace.lumbar;

import java.io.Serializable;

public class Lawyer implements Serializable {
    private String name;
    private int age;
    private String email;
    private String phone;
    private String username;
    private String password;
    private LawFirm firm;
    //TODO: profile pic

    public Lawyer(String name, int age, String email, String phone, String username,
                  String password) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    //for testing
    public String getPassword() {
        return password;
    }

    public void setFirm(LawFirm firm){
        this.firm = firm;
    }

    public LawFirm getFirm() {return firm;}
}
