package com.example.projetdevmob.api;

public class RegisterRequete {
    private String prenom;
    private String nom;
    private String email;
    private String password;
    private String etage;
    private String superficie;
    private String reponse;

    public RegisterRequete(String prenom, String nom, String email, String password, String etage, String superficie, String reponse) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.etage = etage;
        this.superficie = superficie;
        this.reponse = reponse;
    }

    // getters and setters (si besoin)
}
