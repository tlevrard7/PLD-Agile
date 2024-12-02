package com.Bestanome.Plan;

import java.util.ArrayList;


public class PlanFactory {

  public static Plan creerPlan(JSONObject planSchemaJO){
    Plan planVille = new Plan();
    JSONArray noeuds = planJO.getJSONArray("noeud");
    JSONArray troncons = planJO.getJSONArray("troncon");
    
    for(int i = 0; i < noeuds.length(); i++){
      JSONObject jo = noeuds.getJSONObject(i);
      planVille.ajouterPoint(new Point(jo.getLong("id"), jo.getDouble("latitude"), jo.getDouble("longitude"), TypePoint.INTERSECTION));
    }
    
    for(int i = 0; i < troncons.length(); i++){
      JSONObject jo = troncons.getJSONObject(i);
      planVille.ajouterSegment(new Segment(jo.getString("nomRue"), jo.getDouble("longueur"), jo.getLong("origine"), jo.getLong("destination")));
    }
    return plan;
  }


}
