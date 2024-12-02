package com.Bestanome.Plan;

import com.Bestanome.Livraisons.Livraison;
import com.Bestanome.Livraisons.Tournee;
import com.Bestanome.Outils.ParseurXML;
import com.Bestanome.Plan.Plan;
import com.Bestanome.Plan.Point;
import com.Bestanome.Plan.Segment;
import com.Bestanome.Plan.TypePoint;


public class Data {
  private static Plan planVille;
  private static ArrayList<Livraison> livraisonsDues;
  private static ArrayList<Tournee>  tourneesPrevues;
  private static Long idEntrepot;

  public Data(){
    this.planVille = new Plan();
    this.livraisonsDues = new ArrayList<Livraison>();
    this.tourneesPrevues = new ArrayList<Tournee>();
  }

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
