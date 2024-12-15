package com.Bestanome.Model.Outils.TSP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import com.Bestanome.Model.Objets.Livraisons.Livraison;

public class State {
    public Long point;
    public Pair<Pair<Long, Boolean>, Pair<Long, Boolean>>[] deliveryInfo;
    public Double cost;
    public ArrayList<Long> circuit;
    public Map<Long, Boolean> passed;

    // Constructeur par défaut
    public State() {
        this.point = null;
        this.deliveryInfo = null;
        this.cost = Double.MAX_VALUE;
        this.circuit = new ArrayList<>();
        this.passed = new HashMap<>();
    }

    // Constructeur avec point initial et liste de livraisons
    public State(Long initialPoint, ArrayList<Livraison> livraisons) {
        this.point = initialPoint;
        this.deliveryInfo = new Pair[livraisons.size()];

        for (int i = 0; i < livraisons.size(); i++) {
            this.deliveryInfo[i] = Pair.of(
                Pair.of(livraisons.get(i).getPickup(), false),
                Pair.of(livraisons.get(i).getDestination(), false)
            );
        }

        this.cost = 0d;
        this.circuit = new ArrayList<>();
        this.passed = new HashMap<>();
        this.passed.put(initialPoint, true);
        this.circuit.add(initialPoint);
    }

    // Méthode toString pour l'affichage de l'état
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{point: ").append(point);
        sb.append(", cost: ").append(cost);
        sb.append(", circuit: ").append(circuit);
        sb.append(", deliveryInfo: [");

        for (int i = 0; i < deliveryInfo.length; i++) {
            sb.append("{pickup: ")
              .append(deliveryInfo[i].getLeft().getLeft())
              .append(", pickupVisited: ")
              .append(deliveryInfo[i].getLeft().getRight())
              .append(", delivery: ")
              .append(deliveryInfo[i].getRight().getLeft())
              .append(", deliveryVisited: ")
              .append(deliveryInfo[i].getRight().getRight())
              .append("}");
            if (i < deliveryInfo.length - 1) {
                sb.append(", ");
            }
        }

        sb.append("]}");
        return sb.toString();
    }
}