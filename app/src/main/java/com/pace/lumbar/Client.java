package com.pace.lumbar;

public class Client extends User{

    public String caseType;

    public Client(String name, int age, String city, String state, String caseType) {
        super(name, age, city, state);
        this.caseType = caseType;

    }

    public String getCaseType() {
        return caseType;
    }
}
