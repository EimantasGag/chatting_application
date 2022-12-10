package com.eimantasgag.learning_springboot.model;

public class RegisterResponse {
    private int statusCode;
    private String message;

    RegisterResponse(){}

    public RegisterResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
    public int getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
