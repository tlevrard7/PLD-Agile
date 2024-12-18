package com.Bestanome.services;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Bestanome.Model.Data;
import com.Bestanome.Model.Objets.Livraisons.Livraison;
import com.Bestanome.Model.Objets.Livraisons.LivraisonFactory;
import com.Bestanome.Model.Outils.ParseurXML;

@Service
public class LivraisonService {
    public static ArrayList<Livraison> getAllDues() {
        return Data.getLivraisonsDues();
    }

    public static Livraison getDue(Long pickup, Long destination) {
        for (var livraison : Data.getLivraisonsDues()) {
            if (livraison.getPickup().equals(pickup) && livraison.getDestination().equals(destination))
                return livraison;
        }
        return null;
    }

    public static Livraison getDueByPickup(Long pickup) {
        for (var livraison : Data.getLivraisonsDues()) {
            if (livraison.getPickup().equals(pickup))
                return livraison;
        }
        return null;
    }

    public static Livraison getDueByDestination(Long destination) {
        for (var livraison : Data.getLivraisonsDues()) {
            if (livraison.getDestination().equals(destination))
                return livraison;
        }
        return null;
    }
    
    public static void chargerLivraisons(MultipartFile file) throws Exception {
        JSONObject PlanLivraisonsJO = ParseurXML.parseXMLFileContent(file);
        Long idEntrepot = PlanLivraisonsJO.getJSONObject("demandeDeLivraisons").getJSONArray("entrepot").getJSONObject(0).getLong("adresse");
        if (MapService.getPoint(idEntrepot) == null) {
            throw new Exception("Invalid warehouse location");
        }
        Data.resetLivraisons();
        Data.idEntrepot = idEntrepot;
        Data.livraisonsDues = new ArrayList<Livraison>(LivraisonFactory.creerListeLivraisons(PlanLivraisonsJO)
            .stream()
            .filter(l -> MapService.getPoint(l.getPickup()) != null && MapService.getPoint(l.getDestination()) != null)
            .toList()
        );
    }

    public static Long getEntrepot() {
        return Data.idEntrepot;
    }
}
