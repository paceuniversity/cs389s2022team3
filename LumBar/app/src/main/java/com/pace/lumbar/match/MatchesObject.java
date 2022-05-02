package com.pace.lumbar.match;

import android.net.Uri;

public class MatchesObject {

    private String email, name;
    private Uri profileIMGUri;

    public MatchesObject(String email, String name, Uri profileIMGUri){
        this.email = email;
        this.name = name;
        this.profileIMGUri = profileIMGUri;
    }

    public Uri getProfileIMGUri() {
        return profileIMGUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfileIMGUri(Uri profileIMGUri) {
        this.profileIMGUri = profileIMGUri;
    }

    public String getEmail(){
        return email;
    }

    public String setEmail(String email){
        return this.email = email;
    }
}
