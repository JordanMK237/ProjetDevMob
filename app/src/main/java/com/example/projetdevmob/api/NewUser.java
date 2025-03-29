package com.example.projetdevmob.api;

public class NewUser {
    private String prenom;
    private String nom;
    private String email;
    private String mdp;
    private String etage;
    private String superficie;
    private String reponseSecrete;

    public NewUser() {
    }
    public NewUser(String prenom, String nom, String email, String mdp, String etage, String superficie, String reponse_secrete) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.mdp = mdp;
        this.etage = etage;
        this.superficie = superficie;
        this.reponseSecrete = reponseSecrete;
    }
}

