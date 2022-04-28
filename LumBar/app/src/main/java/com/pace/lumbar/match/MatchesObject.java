package com.pace.lumbar.match;

public class MatchesObject {

    private String email, name, profileIMGUri;

    public MatchesObject(String email, String name, String profileIMGUri){
        this.email = email;
        this.name = name;
        this.profileIMGUri = profileIMGUri;
    }

    public String getProfileIMGUri() {
        return profileIMGUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfileIMGUri(String profileIMGUri) {
        this.profileIMGUri = profileIMGUri;
    }

    public String getEmail(){
        return email;
    }

    public String setEmail(String email){
        return this.email = email;
    }
}
