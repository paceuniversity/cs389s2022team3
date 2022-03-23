package com.pace.lumbar;

public class User { //parent class for Client and Lawyer

    String name;
    int age;
    String city;
    String state;
    //TODO: profile pic

    public User(String name, int age, String city, String state) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

}
