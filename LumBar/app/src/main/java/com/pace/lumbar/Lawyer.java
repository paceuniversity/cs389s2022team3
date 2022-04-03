package com.pace.lumbar;

public class Lawyer {
    private String name;
    private int age;
    private String email;
    private int phone;
    private String username;
    private String password;
    //TODO: profile pic

    public Lawyer(String name, int age, String email, int phone, String username,
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

    public int getPhone() {
        return phone;
    }
}
