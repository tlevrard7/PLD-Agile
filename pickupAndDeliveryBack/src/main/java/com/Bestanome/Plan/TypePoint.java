package com.Bestanome.Plan;

public enum TypePoint {
  ENTREPOT, INTERSECTION, PICKUP, DESTINATION;

    public String toString() {
        switch (this) {
            case ENTREPOT: return "ENTREPOT";
            case INTERSECTION: return "INTERSECTION";
            case PICKUP: return "PICKUP";
            case DESTINATION: return "DESTINATION";
            default: return "UNCONNU";
        }
    }
}
