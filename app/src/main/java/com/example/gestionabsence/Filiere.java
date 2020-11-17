package com.example.gestionabsence;

import java.util.Date;

public class Filiere {
    private int id;
    private String description;
    private int anneeScolaire;
    private int numeroGroupe;

    public Filiere(int id, String description, int anneeScolaire, int numeroGroupe) {
        this.id = id;
        this.description = description;
        this.anneeScolaire = anneeScolaire;
        this.numeroGroupe = numeroGroupe;
    }

    public Filiere(String description) {

        this.description = description;

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

    public int getAnneeScolaire() {
        return anneeScolaire;
    }

    public void setAnneeScolaire(int anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }

    public int getNumeroGroupe() {
        return numeroGroupe;
    }

    public void setNumeroGroupe(int numeroGroupe) {
        this.numeroGroupe = numeroGroupe;
    }
}
