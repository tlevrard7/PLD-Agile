package com.Bestanome.Model.Outils.TSP;

import java.util.ArrayList;
import org.apache.commons.lang3.tuple.Pair;

import com.Bestanome.Model.Data;
import com.Bestanome.Model.Objets.Livraisons.Livraison;

public class State {
    public Long point;
    public Pair<Pair<Long, Boolean>, Pair<Long, Boolean>>[] deliveryInfo;

    public State(){
        this.point = null;
        this.deliveryInfo = null;
    }

    public State(ArrayList<Livraison> livraisons){
        this.point = Data.idEntrepot;
        this.deliveryInfo = new Pair[livraisons.size()];
        for(int i = 0; i < livraisons.size(); i++){
            this.deliveryInfo[i] = Pair.of(Pair.of(livraisons.get(i).getPickup(),     false), 
                                           Pair.of(livraisons.get(i).getDestination(),false));
        }
    }
}