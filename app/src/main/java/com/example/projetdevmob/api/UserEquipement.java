package com.example.projetdevmob.api;

public class UserEquipement {
    private int id;
    private int aspirateur;
    private int aspirateur_conso;
    private int fer_repasser;
    private int fer_repasser_conso;
    private int climatiseur;
    private int climatiseur_conso;
    private int machine_a_laver;
    private int machine_a_laver_conso;

    public UserEquipement(int id, int aspi, int aspiConso, int fer, int ferConso, int clim, int climConso, int machineLaver, int machineConso) {
        this.id = id;
        this.aspirateur = aspi;
        this.aspirateur_conso = aspiConso;
        this.fer_repasser = fer;
        this.fer_repasser_conso = ferConso;
        this.climatiseur = clim;
        this.climatiseur_conso = climConso;
        this.machine_a_laver = machineLaver;
        this.machine_a_laver_conso = machineConso;
    }

    public int getId() {
        return id;
    }

    public int getConsoTotale(){
        int total = aspirateur_conso+fer_repasser_conso+climatiseur_conso+machine_a_laver_conso;
        return total;
    }

    public int isAspirateur() {
        return aspirateur;
    }

    public int isFer_repasser() {
        return fer_repasser;
    }

    public int isClimatiseur() {
        return climatiseur;
    }

    public int isMachine_a_laver() {
        return machine_a_laver;
    }

    public int getAspirateur_conso() {
        return aspirateur_conso;
    }

    public int getFer_repasser_conso() {
        return fer_repasser_conso;
    }

    public int getClimatiseur_conso() {
        return climatiseur_conso;
    }

    public int getMachine_a_laver_conso() {
        return machine_a_laver_conso;
    }
}
