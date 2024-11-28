package com.Bestanome;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.Bestanome.Livraisons.Livraison;
import com.Bestanome.Livraisons.Tournee;
import com.Bestanome.Outils.ParseurXML;
import com.Bestanome.Plan.Plan;
import com.Bestanome.Plan.Point;
import com.Bestanome.Plan.Segment;
import com.Bestanome.Plan.TypePoint;

public class Services {
  private Plan planVille;
  private ArrayList<Livraison> livraisonsDues;
  private ArrayList<Tournee>  tourneesPrevues;
  private Long idEntrepot;

  public Services(Long idEntrepot){
    this.planVille = new Plan();
    this.idEntrepot = idEntrepot;

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


  // Fonctions Services
  public Plan chargerPlan(String pathFichier){
    JSONObject planJO = ParseurXML.parseXMLFile(pathFichier).getJSONObject("reseau");
    JSONArray noeuds = planJO.getJSONArray("noeud");
    JSONArray troncons = planJO.getJSONArray("troncon");
    
    for(int i = 0; i < noeuds.length(); i++){
      JSONObject jo = noeuds.getJSONObject(i);
      this.planVille.ajouterPoint(new Point(jo.getLong("id"), jo.getDouble("latitude"), jo.getDouble("longitude"), TypePoint.INTERSECTION));
    }
    
    for(int i = 0; i < troncons.length(); i++){
      JSONObject jo = troncons.getJSONObject(i);
      this.planVille.ajouterSegment(new Segment(jo.getString("nomRue"), jo.getDouble("longueur"), jo.getLong("origine"), jo.getLong("destination")));
    }

    return this.planVille;
  }


}
