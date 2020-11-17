package com.example.gestionabsence;

public class Seance {
    private int id;
    private int heureDebut;
    private int heureFin;
    private String typeSeance;
    private Module module;

    public Seance(int id, int heureDebut, int heureFin, String typeSeance, Module module) {
        this.id = id;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.typeSeance = typeSeance;
        this.module = module;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(int heureDebut) {
        this.heureDebut = heureDebut;
    }

    public int getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(int heureFin) {
        this.heureFin = heureFin;
    }

    public String getTypeSeance() {
        return typeSeance;
    }

    public void setTypeSeance(String typeSeance) {
        this.typeSeance = typeSeance;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }
}
