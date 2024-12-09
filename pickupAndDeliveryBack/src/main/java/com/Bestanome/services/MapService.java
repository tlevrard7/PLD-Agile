package com.Bestanome.services;

import java.io.IOException;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Bestanome.Model.Data;
import com.Bestanome.Model.Objets.Plan.PlanFactory;
import com.Bestanome.Model.Outils.ParseurXML;

@Service
public class MapService {
    // Méthode pour charger un plan à partir d'un fichier XML
    public static void chargerPlan(MultipartFile file) throws IOException {
        // Convertir le contenu du fichier en JSON
        JSONObject planVilleJo = ParseurXML.parseXMLFileContent(file);

        // Construire le Plan à partir du JSON et l'affecter à Data.planVille
        Data.planVille = PlanFactory.creerPlan(planVilleJo);
    }
}