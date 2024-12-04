package com.Bestanome.Model;

import java.util.ArrayList;

import com.Bestanome.Model.Objets.Livraisons.Livraison;
import com.Bestanome.Model.Objets.Livraisons.Tournee;
import com.Bestanome.Model.Objets.Plan.Plan;


public class Data {
  public static Plan planVille;
  public static ArrayList<Livraison> livraisonsDues;
  public static ArrayList<Tournee>  tourneesPrevues;
  public static Long idEntrepot;

  // Setters et Getters
  public void setIdEntrepot(Long idPoint){
    this.idEntrepot = idPoint;
  }

  public void ajouterLivraison(Livraison liv){
    this.livraisonsDues.add(liv);
  }

  public void ajouterTournee(Tournee tournee){
    this.tourneesPrevues.add(tournee);
  }

 
}
