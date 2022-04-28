package com.pace.lumbar.account;

public class Case {

    private String email;
    private String caseType;
    private String caseDetails;

    public Case(String email, String caseType, String caseDetails) {
        this.email = email;
        this.caseType = caseType;
        this.caseDetails = caseDetails;
    }

    public Case() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
