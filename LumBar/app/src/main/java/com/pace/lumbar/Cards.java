package com.pace.lumbar;

public class Cards {
    private String userID;
    private String name;

    public Cards(String userID, String name){
        this.userID = userID;
        this.name = name;
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

    public String setName(String name){
        return this.name = name;
    }
}
