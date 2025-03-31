package com.example.projetdevmob.api;

public class AjoutAppareilRequete {
    private int user_id;
    private String appareil;
    private int appareil_conso;



    public AjoutAppareilRequete(int user_id, String appareil, int appareil_conso) {
        this.user_id = user_id;
        this.appareil = appareil;
        this.appareil_conso = appareil_conso;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getAppareil() {
        return appareil;
    }

    public int getAppareilConso() {
        return appareil_conso;
    }

}
