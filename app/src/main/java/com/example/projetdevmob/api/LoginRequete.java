package com.example.projetdevmob.api;

public class LoginRequete {
    private String email;
    private String mdp;

    public LoginRequete(String email, String mdp) {
        this.email = email;
        this.mdp = mdp;
    }

    // Getters (et setters si besoin)
    public String getEmail() {
        return email;
    }
    public String getMdp() {
        return mdp;
    }
}

