package com.Bestanome.Model.dto;

import java.util.List;

import com.Bestanome.Model.Objets.Livraisons.Livraison;
import com.Bestanome.Model.Objets.Livraisons.Livreur;

public class LivreurDTO {
    private int id;
    private String nom;
    private String prenom;
    private int nombreDeLivraisons;
    private List<Livraison> livraisons; // Remplacer livraisonsIds par une liste de livraisons

    // Constructeur par défaut
    public LivreurDTO() {}

    // Constructeur à partir de la classe Livreur
    public LivreurDTO(Livreur livreur) {
        this.id = livreur.getId();
        this.nom = livreur.getNom();
        this.prenom = livreur.getPrenom();
        this.nombreDeLivraisons = livreur.getNombreDeLivraisons();
        this.livraisons = livreur.getLivraisonsAssignees(); // Utiliser directement la liste des livraisons
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

    public int getNombreDeLivraisons() {
        return nombreDeLivraisons;
    }

    public List<Livraison> getLivraisons() {
        return livraisons;
    }

    @Override
    public String toString() {
        return "LivreurDTO{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nombreDeLivraisons=" + nombreDeLivraisons +
                ", livraisons=" + livraisons +
                '}';
    }
}