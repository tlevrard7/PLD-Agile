package com.Bestanome.services;

import java.io.IOException;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Bestanome.Model.Data;
import com.Bestanome.Model.Objets.Livraisons.LivraisonFactory;
import com.Bestanome.Model.Objets.Livraisons.Tournee;
import com.Bestanome.Model.Outils.ParseurXML;
import com.Bestanome.Model.Outils.TSP.TSPRunner;

@Service
public class TourneeService {
    // Méthode pour charger des livraisons à partir d'un fichier XML
    public static void chargerLivraisons(MultipartFile file) throws IOException {
        JSONObject PlanLivraisonsJO = ParseurXML.parseXMLFileContent(file);
        Data.livraisonsDues = LivraisonFactory.creerListeLivraisons(PlanLivraisonsJO);
        Data.idEntrepot = PlanLivraisonsJO.getJSONObject("demandeDeLivraisons").getJSONArray("entrepot").getJSONObject(0).getLong("adresse");

        TSPRunner tspRunner = new TSPRunner();
        tspRunner.loadMap(Data.planVille);
        Tournee t = new Tournee(Data.livraisonsDues);
        tspRunner.findCircuit(t);
    }
}
    
    