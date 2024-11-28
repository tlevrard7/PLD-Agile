package com.Bestanome.Livraisons;

import java.util.ArrayList;
import com.Bestanome.Livraisons.Livraison;
import com.Bestanome.Livraisons.Circuit;
import com.Bestanome.Plan.Segment;

public class Tournee {
  private ArrayList<Livraison> livraisons;
  private Circuit circuit;

  public Tournee(){
    this.circuit = new Circuit();
    this.livraisons = new ArrayList<Livraison>();
  }

  public void ajouterLivraison(Livraison liv){
    this.livraisons.add(liv);
  }

  public void ajouterSegCircuit(Segment segment){
    this.circuit.ajouterSegment(segment);
  }

  public ArrayList<Livraison> livraisons(){
    return this.livraisons;
  } 

  public Circuit circuit(){
    return this.circuit;
  } 

}
