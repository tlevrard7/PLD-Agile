package com.Bestanome.Model.Outils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.web.multipart.MultipartFile;

import com.Bestanome.Model.Data;
import com.Bestanome.Model.Objets.Livraisons.Livraison;

public class ParseurLivraisonXML {

    public static ArrayList<Livraison> parseXMLFileContent(MultipartFile file) {
        try {
            // Lire le contenu du fichier XML
            String xmlContent = new String(file.getBytes(), StandardCharsets.UTF_8);
            JSONObject jsonObject = XML.toJSONObject(xmlContent);

            // Extraire l'entrepôt
            JSONObject demandeDeLivraisons = jsonObject.getJSONObject("demandeDeLivraisons");
            JSONObject entrepot = demandeDeLivraisons.getJSONObject("entrepot");
            long adresseEntrepot = entrepot.getLong("adresse");
            Data.idEntrepot = adresseEntrepot;

            // Extraire les livraisons
            ArrayList<Livraison> livraisons = new ArrayList<>();
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
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}