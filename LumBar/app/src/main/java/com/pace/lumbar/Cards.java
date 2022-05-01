package com.pace.lumbar;

public class Cards {
    private String userID;
    private String name;
    private String firmName;

    //Card for Client
    public Cards(String userID, String name){
        this.userID = userID;
        this.name = name;
    }

    //Card for Lawyer
    public Cards(String userID, String name, String firmName){
        this.userID = userID;
        this.name = name;
        this.firmName = firmName;
    }

    public String getUserID(){
        return userID;
    }

    public String setUserID(String userID){
        return this.userID = userID;
    }

    public String getName(){
        return name;
    }

    public String getFirmName(){
        return firmName;
    }

    public String setName(String name){
        return this.name = name;
    }
}
