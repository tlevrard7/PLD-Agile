package com.Bestanome.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.Bestanome.Model.Data;
import com.Bestanome.Model.Objets.Plan.PlanFactory;
import com.Bestanome.Model.Objets.Plan.Point;
import com.Bestanome.Model.Objets.Plan.Segment;
import com.Bestanome.Model.Objets.Plan.Plan;
import com.Bestanome.Model.Objets.Plan.TypePoint;
import com.Bestanome.Model.Outils.ParseurXML;
import com.Bestanome.Model.dto.PlanDTO;
import com.Bestanome.Model.dto.PointDTO;

@Service
public class MapService {

    // @RequestMapping("/")
    String home() {
        return ParseurXML.parseXMLFileContent("../fichiersXMLPickupDelivery/myDeliverRequest.xml").toString(4);
        // return "Hello World!";
    }

    public PointDTO getRandomPoint() throws InterruptedException {
        Random rand = new Random();
        Point point = new Point(0L, rand.nextDouble(-90, 90), rand.nextDouble(-180, 180), TypePoint.INTERSECTION);
        Thread.sleep(500); // Hardcoded delay to simulate compute time

        return PointDTO.fromPoint(point);
    }

    public Plan parseMapFile(MultipartFile file) throws IOException {
        // Lire le contenu du fichier XML
        String xmlContent = new String(file.getBytes(), StandardCharsets.UTF_8);

        // Convertir en JSON
        JSONObject jsonObject = ParseurXML.parseXMLFileContent(xmlContent);

        // Construire le Plan à partir du JSON
        Plan plan = new Plan();
        JSONArray noeuds = jsonObject.getJSONObject("reseau").getJSONArray("noeud");
        JSONArray troncons = jsonObject.getJSONObject("reseau").getJSONArray("troncon");

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
            System.out.println("Troncon data: " + troncon.toString(4)); // Debugging log
            // Access keys
            String nomRue = troncon.getString("nomRue");
            double longueur = troncon.getDouble("longueur");
            long origine = troncon.getLong("origine");
            long destination = troncon.getLong("destination");

            System.out.printf("Adding segment: %s, %.2f, %d -> %d%n", nomRue, longueur, origine, destination);
            plan.ajouterSegment(new Segment(nomRue, longueur, origine, destination));
        }

        return plan;
    }
}
