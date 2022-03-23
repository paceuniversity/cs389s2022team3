package com.pace.lumbar;

public class Lawyer extends User{

    String[] specialties; //law specializations (injury, criminal, medical, etc.)
    String firmName; //do they work for a larger firm or have there own practice?


    public Lawyer(String name, int age, String city, String state,
                  String[] specialties, String firmName) {
        super(name, age, city, state);
        this.specialties = specialties.clone();
    }

    public String getFirmName() {
        return firmName;
    }

    public String[] getSpecialties() {
        return specialties;
    }
}
