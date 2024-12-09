package com.Bestanome.services;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Bestanome.Model.Data;
import com.Bestanome.Model.Objets.Livraisons.Livraison;
import com.Bestanome.Model.Objets.Plan.PlanFactory;
import com.Bestanome.Model.Objets.Plan.Point;
import com.Bestanome.Model.Objets.Plan.TypePoint;
import com.Bestanome.Model.Outils.ParseurLivraisonXML;
import com.Bestanome.Model.Outils.ParseurXML;

@Service
public class MapService {

    // Méthode de test pour obtenir un point aléatoire
    public static Point getRandomPoint() throws InterruptedException {
        Random rand = new Random();
        Point point = new Point(0L, rand.nextDouble(-90, 90), rand.nextDouble(-180, 180), TypePoint.INTERSECTION);
        Thread.sleep(500); // Délai simulé pour tester le temps de calcul

        return point;
    }

    // Méthode pour charger un plan à partir d'un fichier XML
    public static void chargerPlan(MultipartFile file) throws IOException {
        // Convertir le contenu du fichier en JSON
        JSONObject planVilleJo = ParseurXML.parseXMLFileContent(file);

        // Construire le Plan à partir du JSON et l'affecter à Data.planVille
        Data.planVille = PlanFactory.creerPlan(planVilleJo);
    }

    // Méthode pour charger des livraisons à partir d'un fichier XML
    public static void chargerLivraisons(MultipartFile file) throws IOException {
        List<Livraison> livraisons = ParseurLivraisonXML.parseXMLFileContent(file);
        Data.livraisonsDues = livraisons;
    }
}