package com.Bestanome.Model.Outils.TSP;

import org.apache.commons.lang3.tuple.Pair;

public class State {
    public Long point;
    public Pair<Pair<Long, Boolean>, Pair<Long, Boolean>>[] deliveryInfo;
}