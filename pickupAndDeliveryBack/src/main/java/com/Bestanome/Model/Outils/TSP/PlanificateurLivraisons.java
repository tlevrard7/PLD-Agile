package com.Bestanome.Model.Outils.TSP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.Bestanome.Model.Data;
import com.Bestanome.Model.Objets.Livraisons.Livraison;
import static com.Bestanome.Model.Outils.TSP.TSPRunner.getHaversineLength;

public class PlanificateurLivraisons {

    @FunctionalInterface
    public interface Heuristique {
        int calculer(Etat etat);
    }

    public static class Etat {
        public Long point;
        public Map<Long, ArrayList<Long>> pickUPtoDelivery;
        public ArrayList<Long> circuit;
        public ArrayList<Long> ouverts;
    
        public Etat(ArrayList<Livraison> livraisons) {
            this.point = Data.idEntrepot;
            this.ouverts = new ArrayList<>();
            this.pickUPtoDelivery = new HashMap<>();
    
            for (Livraison livraison : livraisons) {
                pickUPtoDelivery.computeIfAbsent(livraison.getPickup(), k -> new ArrayList<>())
                                .add(livraison.getDestination());
                if (!ouverts.contains(livraison.getPickup())) {
                    ouverts.add(livraison.getPickup());
                }
            }
            this.circuit = new ArrayList<>();
            this.circuit.add(Data.idEntrepot);
        }
    
        @Override
        public String toString() {
            String s = "{point: " + this.point.toString() + ", ouverts: [";
            for (int i = 0; i < ouverts.size(); i++) {
                s += ouverts.get(i).toString();
                if (i < ouverts.size() - 1) {
                    s += ", ";
                }
            }
            s += "], circuit: " + circuit.toString() + "}";
            return s;
        }
    }


    public static ArrayList<Long> ordonnancer(ArrayList<Livraison> livraisons, Heuristique h) {
        Etat etatActuel = new Etat(livraisons);
        ArrayList<Long> planning = new ArrayList<>();
        planning.add(Data.idEntrepot);
    
        System.out.println(etatActuel);
    
        for (int i = 0; i < livraisons.size() * 2; i++) {
            if (etatActuel.ouverts.isEmpty()) {
                System.out.println("Aucun point ouvert disponible pour le traitement.");
                break;
            }
    
            // Choisir le prochain point
            int hIndex = h.calculer(etatActuel);
            if (hIndex < 0 || hIndex >= etatActuel.ouverts.size()) {
                System.out.println("Index heuristique hors limites : " + hIndex);
                break;
            }
    
            Long choixHeuristique = etatActuel.ouverts.remove(hIndex);
            planning.add(choixHeuristique);
    
            // Changer l'état
            etatActuel.point = choixHeuristique;
            ArrayList<Long> pointsLivraison = etatActuel.pickUPtoDelivery.get(choixHeuristique);
            if (pointsLivraison != null) {
                etatActuel.ouverts.addAll(pointsLivraison);
            }
        }
    
        planning.add(Data.idEntrepot);
        return planning;
    }

    public static int NNLivraisons(Etat etat) {
        Double min = Double.MAX_VALUE;
        int minIndex = 0;
        int i = 0;
        for (Long pointPossible : etat.ouverts) {
            Double longeur = getHaversineLength(etat.point, pointPossible);
            if (longeur < min) {
                minIndex = i;
                min = longeur;
            }
            i++;
        }
        return minIndex;
    }

}
