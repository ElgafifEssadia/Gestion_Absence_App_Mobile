package com.example.gestionabsence;

public class Module {
    private int id;
    private String description;
    private int horaire;
    private Filiere filiere;

    public Module(int id, String description, int horaire, Filiere filiere) {
        this.id = id;
        this.description = description;
        this.horaire = horaire;
        this.filiere = filiere;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHoraire() {
        return horaire;
    }

    public void setHoraire(int horaire) {
        this.horaire = horaire;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }
}
