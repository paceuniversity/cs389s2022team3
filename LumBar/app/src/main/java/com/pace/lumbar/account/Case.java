package com.pace.lumbar.account;

public class Case {

    private String clientEmail;
    private String caseType;
    private String caseDetails;

    public Case(String clientEmail, String caseType, String caseDetails) {
        this.clientEmail = clientEmail;
        this.caseType = caseType;
        this.caseDetails = caseDetails;
    }

    public Case() {

    }

    public String getClientEmail() {
        return clientEmail;
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
