package com.example.gestionabsence;

public class Absence {
    private String justification;
    private String typeSeance;
    private String nom;


    public Absence(String nom, String typeSeance, String justification) {
        this.nom = nom;
        this.typeSeance = typeSeance;
        this.justification = justification;
    }

    public Absence( String justification) {

        this.justification = justification;
    }

    public Absence() {

    }


    public String getTypeSeance() {
        return typeSeance;
    }

    public void setTypeSeance(String typeSeance) {
        this.typeSeance = typeSeance;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }



    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }
}
