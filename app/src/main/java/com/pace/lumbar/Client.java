package com.pace.lumbar;

public class Client { //parent class for Client and Lawyer

    private String name;
    private int age;
    private String city;
    private String state;
    private String username;
    private String password;
    private String caseType;
    //TODO: profile pic

    public Client(String name, int age, String city, String state, String username,
                  String password, String caseType) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.state = state;
        this.username = username;
        this.password = password;
        this.caseType = caseType;
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

    public String getCaseType() {
        return caseType;
    }

}
