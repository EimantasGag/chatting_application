package com.eimantasgag.learning_springboot.model;

public class LoginResponse {
    private int statusCode;
    private String message;

    public LoginResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    LoginResponse(){}

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    } 
}
