package com.Bestanome.Model.Objets.Plan;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Segment {

    @JsonProperty
    private String nomRue;

    @JsonProperty
    private double longueur;

    @JsonProperty
    private long origine;

    @JsonProperty
    private long destination;

    // Constructeur par défaut requis par Jackson
    public Segment() {
    }

    // Constructeur complet
    public Segment(String nomRue, double longueur, long origine, long destination) {
        this.nomRue = nomRue;
        this.longueur = longueur;
        this.origine = origine;
        this.destination = destination;
    }

    // Getters et Setters (requis pour Jackson)
    public String getNomRue() {
        return nomRue;
    }

    public void setNomRue(String nomRue) {
        this.nomRue = nomRue;
    }

    public double getLongueur() {
        return longueur;
    }

    public void setLongueur(double longueur) {
        this.longueur = longueur;
    }

    public long getOrigine() {
        return origine;
    }

    public void setOrigine(long origine) {
        this.origine = origine;
    }

    public long getDestination() {
        return destination;
    }

    public void setDestination(long destination) {
        this.destination = destination;
    }
}
