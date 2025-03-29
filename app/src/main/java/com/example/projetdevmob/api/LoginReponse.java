package com.example.projetdevmob.api;

public class LoginReponse {
    private boolean succes;
    private String message;
    private User user;

    public boolean isSuccess() {
        return succes;
    }
    public String getMessage() {
        return message;
    }
    public User getUser() {
        return user;
    }
}

