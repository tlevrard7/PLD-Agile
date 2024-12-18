package com.Bestanome.services;

import java.io.IOException;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Bestanome.Model.Data;
import com.Bestanome.Model.Objets.Plan.Plan;
import com.Bestanome.Model.Objets.Plan.PlanFactory;
import com.Bestanome.Model.Objets.Plan.Point;
import com.Bestanome.Model.Outils.ParseurXML;
import com.Bestanome.Model.Outils.TSP.TSPRunner;

@Service
public class MapService {
    // Méthode pour charger un plan à partir d'un fichier XML
    public static void chargerPlan(MultipartFile file) throws IOException {
        // Convertir le contenu du fichier en JSON
        JSONObject planVilleJo = ParseurXML.parseXMLFileContent(file);
        
        // Construire le Plan à partir du JSON et l'affecter à Data.planVille
        Data.reset();
        Data.planVille = PlanFactory.creerPlan(planVilleJo);

        // Initialiser le TSPRunner avec le plan chargé
        TSPRunner.initiate(Data.getPlanVille());
    }

    public static Point getPoint(Long id) {
        for (var point : Data.getPlanVille().getPoints()) {
            if (point.getId().equals(id))
                return point;
        }
        return null;
    }
    public static Plan getPlan() {
        return Data.getPlanVille();
    }
}