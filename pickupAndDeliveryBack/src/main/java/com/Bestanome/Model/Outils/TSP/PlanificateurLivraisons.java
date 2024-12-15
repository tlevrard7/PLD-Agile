package com.Bestanome.Model.Outils.TSP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.Bestanome.Model.Data;
import com.Bestanome.Model.Objets.Livraisons.Livraison;
import static com.Bestanome.Model.Outils.TSP.TSPRunner.getHaversineLength;


public class PlanificateurLivraisons {

  public static class Etat{
      public Long point;
      public Map<Long, Long> pickUPtoDelivery;
      public ArrayList<Long> circuit;
      public ArrayList<Long> ouverts;

      public Etat(ArrayList<Livraison> livraisons){
          this.point = Data.idEntrepot;
          this.ouverts = new ArrayList<>();
          this.pickUPtoDelivery = new HashMap<>();
          for(Livraison livraison : livraisons){
              ouverts.add(livraison.getPickup());
              pickUPtoDelivery.put(livraison.getPickup(), livraison.getDestination());
          }
          this.circuit = new ArrayList<>();
          this.circuit.add(Data.idEntrepot);
      }

      public String toString(){
          String s = "{point: " + this.point.toString() + ", ouverts: [";
          int i;
          for(i = 0; i < ouverts.size() - 1; i++){
              s += ouverts.get(i).toString() + ", ";
          }
          s += ouverts.get(i).toString() + "], circuit: " + circuit.toString() + "}";
          return s;
      }
  }

  @FunctionalInterface
  interface Heuristique {
      int calculer(Etat s);
  }

  public static ArrayList<Long> ordonnancer(ArrayList<Livraison> livraisons, Heuristique h){
      Etat etatActuel = new Etat(livraisons);
      ArrayList<Long> planning = new ArrayList<>();
      planning.add(Data.idEntrepot);
      System.out.println(etatActuel);
      for(int i = 0; i < livraisons.size()*2; i++){
          // Choissir le prochain point
          int hIndex = h.calculer(etatActuel);
          Long choixHeuristique = etatActuel.ouverts.remove(hIndex);
          planning.add(choixHeuristique);
          //Changer l'état
          etatActuel.point = choixHeuristique;
          Long pointLivraison = etatActuel.pickUPtoDelivery.get(choixHeuristique);
          if(pointLivraison != null) etatActuel.ouverts.add(pointLivraison);
      }
      planning.add(Data.idEntrepot);
      return planning;
  }

  public static int NNLivraisons(Etat s){
      Double min = Double.MAX_VALUE;
      int minIndex = 0;
      int i = 0;
      for(Long pointPossible: s.ouverts){
          Double longeur = getHaversineLength(s.point, pointPossible);
          if(longeur < min){
            minIndex = i;
            min = longeur;
          }
          i++;
      }
      return minIndex;
  }

}
