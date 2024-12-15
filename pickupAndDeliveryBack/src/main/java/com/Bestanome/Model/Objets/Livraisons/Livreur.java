package com.Bestanome.Model.Objets.Livraisons;

import java.util.ArrayList;
import java.util.List;

public class Livreur {
    private int id;
    private String nom;
    private String prenom;
    private List<Livraison> livraisonsAssignees;

    // Constructeur
    public Livreur(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.livraisonsAssignees = new ArrayList<>();
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public List<Livraison> getLivraisonsAssignees() {
        return livraisonsAssignees;
    }

    // Méthode pour obtenir le nombre de livraisons
    public int getNombreDeLivraisons() {
        return livraisonsAssignees.size();
    }

    // Méthode pour assigner une livraison
    public void assignerLivraison(Livraison livraison) {
        this.livraisonsAssignees.add(livraison);
    }
    

    @Override
    public String toString() {
        return "Livreur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nombreDeLivraisons=" + getNombreDeLivraisons() +
                '}';
    }
}