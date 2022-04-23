package com.pace.lumbar.account;

public class Case {

    private String clientUsername;
    private String caseType;
    private String caseDetails;

    public Case(String username, String caseType, String caseDetails) {
        this.clientUsername = username;
        this.caseType = caseType;
        this.caseDetails = caseDetails;
    }

    public Case() {

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

    public void setCaseDetails(String caseDetails) {
        this.caseDetails = caseDetails;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }
}
