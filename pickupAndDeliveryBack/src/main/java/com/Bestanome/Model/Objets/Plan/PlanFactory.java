package com.Bestanome.Model.Objets.Plan;

import org.json.JSONArray;
import org.json.JSONObject;

public class PlanFactory {

  public static Plan creerPlan(JSONObject planSchemaJO) {

    Plan plan = new Plan();
    JSONArray noeuds = planSchemaJO.getJSONObject("reseau").getJSONArray("noeud");
    JSONArray troncons = planSchemaJO.getJSONObject("reseau").getJSONArray("troncon");

    for (int i = 0; i < noeuds.length(); i++) {
      JSONObject node = noeuds.getJSONObject(i);
      plan.ajouterPoint(new Point(
          node.getLong("id"),
          node.getDouble("latitude"),
          node.getDouble("longitude"),
          TypePoint.INTERSECTION));
    }

    for (int i = 0; i < troncons.length(); i++) {
      JSONObject troncon = troncons.getJSONObject(i);
      // Access keys
      String nomRue = troncon.getString("nomRue");
      double longueur = troncon.getDouble("longueur");
      long origine = troncon.getLong("origine");
      long destination = troncon.getLong("destination");
      plan.ajouterSegment(new Segment(nomRue, longueur, origine, destination));
    }

    return plan;
  }

}
