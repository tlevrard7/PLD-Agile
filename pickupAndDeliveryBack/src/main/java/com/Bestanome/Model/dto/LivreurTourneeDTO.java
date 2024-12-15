package com.Bestanome.Model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LivreurTourneeDTO {

    @JsonProperty("nom")
    private String nom;

    @JsonProperty("prenom")
    private String prenom;

    @JsonProperty("circuit")
    private CircuitDTO circuit;

    public LivreurTourneeDTO(String nom, String prenom, CircuitDTO circuit) {
        this.nom = nom;
        this.prenom = prenom;
        this.circuit = circuit;
    }

    // Getters et setters (si nécessaires)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public CircuitDTO getCircuit() {
        return circuit;
    }

    public void setCircuit(CircuitDTO circuit) {
        this.circuit = circuit;
    }
}