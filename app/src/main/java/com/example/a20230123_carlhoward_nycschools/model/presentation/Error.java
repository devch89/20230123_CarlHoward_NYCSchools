package com.example.a20230123_carlhoward_nycschools.model.presentation;

public class Error extends PresentationData {
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}