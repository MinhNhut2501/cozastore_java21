package com.cybersoft.cozastore_21.exception;

public class UserNotFoundException extends RuntimeException {
    private String message;
    UserNotFoundException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
