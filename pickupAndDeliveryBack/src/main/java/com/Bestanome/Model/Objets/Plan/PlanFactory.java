package com.Bestanome.Model.Objets.Plan;

import org.json.JSONArray;
import org.json.JSONObject;

public class PlanFactory {

  public static Plan creerPlan(JSONObject planSchemaJO) {
    Plan planVille = new Plan();
    JSONArray noeuds = planSchemaJO.getJSONArray("noeud");
    JSONArray troncons = planSchemaJO.getJSONArray("troncon");

    for (int i = 0; i < noeuds.length(); i++) {
      JSONObject jo = noeuds.getJSONObject(i);
      planVille.ajouterPoint(
          new Point(jo.getLong("id"), jo.getDouble("latitude"), jo.getDouble("longitude"), TypePoint.INTERSECTION));
    }

    for (int i = 0; i < troncons.length(); i++) {
      JSONObject jo = troncons.getJSONObject(i);
      planVille.ajouterSegment(new Segment(jo.getString("nomRue"), jo.getDouble("longueur"), jo.getLong("origine"),
          jo.getLong("destination")));
    }
    return plan;
  }

}
