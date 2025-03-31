package com.example.projetdevmob.api;

public class ApiResponse {
    private boolean success;
    private String message;
    private int nb_appareils; // utile si ton API retourne le nombre mis à jour

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getNb_appareils() {
        return nb_appareils;
    }
}
