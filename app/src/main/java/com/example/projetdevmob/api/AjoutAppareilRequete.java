package com.example.projetdevmob.api;

public class AjoutAppareilRequete {
    private int user_id;
    private String appareil;

    public AjoutAppareilRequete(int user_id, String appareil) {
        this.user_id = user_id;
        this.appareil = appareil;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getAppareil() {
        return appareil;
    }
}
