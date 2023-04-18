package com.eimantasgag.learning_springboot.model;

public class RegisterResponse {
    private boolean success;
    private String message;

    RegisterResponse(){}

    public RegisterResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    public boolean isSuccess() {
        return success;
    }
    public void setStatusCode(boolean success) {
        this.success = success;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
