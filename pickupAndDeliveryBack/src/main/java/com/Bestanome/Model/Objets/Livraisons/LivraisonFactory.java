package com.Bestanome.Model.Objets.Livraisons;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class LivraisonFactory {

  public static ArrayList<Livraison> creerLivraison(JSONObject planSchemaJO) {

    ArrayList<Livraison> livraisons = new ArrayList<Livraison>();

    JSONObject demandeDeLivraisons = planSchemaJO.getJSONObject("demandeDeLivraisons");

    // Extraire les livraisons
    JSONArray livraisonsArray = demandeDeLivraisons.optJSONArray("livraison");
    if (livraisonsArray == null) {
        livraisonsArray = new JSONArray();
        livraisonsArray.put(demandeDeLivraisons.getJSONObject("livraison"));
    }

    for (int i = 0; i < livraisonsArray.length(); i++) {
        JSONObject livraisonObject = livraisonsArray.getJSONObject(i);
        long pickup = livraisonObject.getLong("adresseEnlevement");
        long destination = livraisonObject.getLong("adresseLivraison");
        int dureeEnlevement = livraisonObject.getInt("dureeEnlevement");
        int dureeLivraison = livraisonObject.getInt("dureeLivraison");

        livraisons.add(new Livraison(pickup, destination, dureeEnlevement, dureeLivraison));
    }

    return livraisons;
  }

}
