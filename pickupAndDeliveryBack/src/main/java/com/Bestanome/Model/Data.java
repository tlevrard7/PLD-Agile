package com.Bestanome.Model;

import java.util.ArrayList;
import java.util.List;

import com.Bestanome.Model.Objets.Livraisons.Livraison;
import com.Bestanome.Model.Objets.Livraisons.Livreur;
import com.Bestanome.Model.Objets.Livraisons.Tournee;
import com.Bestanome.Model.Objets.Plan.Plan;

public class Data {
    public static Plan planVille;
    public static ArrayList<Livraison> livraisonsDues = new ArrayList<>();
    public static ArrayList<Tournee> tourneesPrevues = new ArrayList<>();
    public static Long idEntrepot;
    public static List<Livreur> livreurs = new ArrayList<>();

    // Setter pour idEntrepot
    public static void setIdEntrepot(Long idPoint) {
        Data.idEntrepot = idPoint;
    }

    // Ajouter une livraison
    public static void ajouterLivraison(Livraison liv) {
        Data.livraisonsDues.add(liv);
    }

    // Ajouter une tournée
    public static void ajouterTournee(Tournee tournee) {
        Data.tourneesPrevues.add(tournee);
    }

    // Getter pour le plan de la ville
    public static Plan getPlanVille() {
        return Data.planVille;
    }

    // Getter pour les livraisons dues
    public static ArrayList<Livraison> getLivraisonsDues() {
        return new ArrayList<>(Data.livraisonsDues); // Retourne une copie pour éviter les modifications directes
    }

    // Getter pour les tournées prévues
    public static ArrayList<Tournee> getTourneesPrevues() {
        return new ArrayList<>(Data.tourneesPrevues); // Retourne une copie pour éviter les modifications directes
    }

    // Méthode pour réinitialiser les données
    public static void reset() {
        Data.planVille = null;
        Data.livraisonsDues.clear();
        Data.tourneesPrevues.clear();
        Data.idEntrepot = null;
    }

    // Méthode pour initialiser les livreurs
    public static void initialiserLivreurs() {
        livreurs.add(new Livreur(1, "Corentin", "Jeanne"));
        livreurs.add(new Livreur(2, "Harold", "Martin"));
        livreurs.add(new Livreur(3, "Saad", "Elghissassi"));
        livreurs.add(new Livreur(4, "Thomas", "Levrard"));
    }

    // Getter pour les livreurs
    public static List<Livreur> getLivreurs() {
        return livreurs;
    }
}