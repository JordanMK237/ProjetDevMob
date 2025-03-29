package com.example.projetdevmob.api;

import com.google.gson.annotations.SerializedName;

public class RegisterRequete {
    private String prenom;
    private String nom;
    private String email;

    // On envoie le mot de passe sous la clé "mdp"
    @SerializedName("mdp")
    private String password;

    private String etage;
    private String superficie;

    // Le serveur attend la clé "reponse_secrete"
    @SerializedName("reponse_secrete")
    private String secretAnswer;

    public RegisterRequete(String prenom, String nom, String email, String password, String etage, String superficie, String secretAnswer) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.etage = etage;
        this.superficie = superficie;
        this.secretAnswer = secretAnswer;
    }

    // Getters et setters si nécessaire

    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEtage() {
        return etage;
    }
    public void setEtage(String etage) {
        this.etage = etage;
    }
    public String getSuperficie() {
        return superficie;
    }
    public void setSuperficie(String superficie) {
        this.superficie = superficie;
    }
    public String getSecretAnswer() {
        return secretAnswer;
    }
    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
    }
}
