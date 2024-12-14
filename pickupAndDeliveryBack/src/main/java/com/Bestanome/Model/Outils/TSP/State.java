package com.Bestanome.Model.Outils.TSP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import com.Bestanome.Model.Data;
import com.Bestanome.Model.Objets.Livraisons.Livraison;

public class State {
    public Long point;
    public Pair<Pair<Long, Boolean>, Pair<Long, Boolean>>[] deliveryInfo;
    public Double cost;
    public ArrayList<Long> circuit;
    public Map<Long, Boolean> passed;

    public State(){
        this.point = null;
        this.deliveryInfo = null;
        this.cost = Double.MAX_VALUE;
        this.circuit = new ArrayList<>();
        this.passed = new HashMap<>();
    }

    public State(Long initalPoint, ArrayList<Livraison> livraisons){
        this.point = initalPoint;
        this.deliveryInfo = new Pair[livraisons.size()];
        for(int i = 0; i < livraisons.size(); i++){
            this.deliveryInfo[i] = Pair.of(Pair.of(livraisons.get(i).getPickup(),     false), 
                                           Pair.of(livraisons.get(i).getDestination(),false));
        }
        this.cost = 0d;
        this.circuit = new ArrayList<>();
        this.passed = new HashMap<>();
        this.passed.put(initalPoint, true);
        this.circuit.add(initalPoint);
    }

    public String toString(){
      String s = "{point: " + this.point.toString() + ", cost: " + this.cost.toString() + ", delivery: " + deliveryInfo[0].toString() + "}";
      return s;
    }
}