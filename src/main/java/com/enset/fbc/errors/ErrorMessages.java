package com.enset.fbc.errors;

import lombok.NoArgsConstructor;
@NoArgsConstructor
public enum ErrorMessages {
    MISSING_REQUIRED_FIELD("Missing required field !!."),
    RECORD_ALREADY_EXISTS("Record already exists !!."),
    INTERNAL_SERVER_ERROR("Internal server error !!."),
    NO_RECORD_FOUND("Record with provided id is not found !! .");

    private String erroMessage;

    ErrorMessages(String erroMessage) {
        this.erroMessage = erroMessage;
    }

    public String getErroMessage() {
        return erroMessage;
    }

    public void setErroMessage(String erroMessage) {
        this.erroMessage = erroMessage;
    }
}
