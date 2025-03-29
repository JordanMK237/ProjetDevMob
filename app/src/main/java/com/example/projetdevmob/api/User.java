package com.example.projetdevmob.api;

public class User {
    private int id;
    private String prenom;
    private String nom;
    private String email;
    private String etage;
    private String superficie;
    private String reponseSecrete;

    public User(int id, String prenom, String nom, String email, String etage, String superficie, String reponse_secrete) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.etage = etage;
        this.superficie = superficie;
        this.reponseSecrete = reponseSecrete;
    }

    public int getId() {return id;}
    public String getPrenom() {return prenom;}
    public String getNom() {return nom;}
    public String getEmail() {return email;}
    public String getEtage() {return etage;}
    public String getSuperficie() {return superficie;}

}

