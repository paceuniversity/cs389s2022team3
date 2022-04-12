package com.pace.lumbar;

public class Case {

    private String clientUsername;
    private String caseType;
    private String caseDetails;

    public Case(String username, String caseType, String caseDetails) {
        this.clientUsername = username;
        this.caseType = caseType;
        this.caseDetails = caseDetails;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public String getCaseType() {
        return caseType;
    }

    public String getCaseDetails() {
        return caseDetails;
    }
}
