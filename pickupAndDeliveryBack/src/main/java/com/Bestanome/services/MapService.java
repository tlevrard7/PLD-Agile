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

    public Point getRandomPoint() throws InterruptedException {
        Random rand = new Random();
        Point point = new Point(0L, rand.nextDouble(-90, 90), rand.nextDouble(-180, 180), TypePoint.INTERSECTION);
        Thread.sleep(500); // Hardcoded delay to simulate compute time

        return point;
    }

    public void chargerPlan(MultipartFile file) throws IOException {
        // Lire le contenu du fichier XML
        String xmlContent = new String(file.getBytes(), StandardCharsets.UTF_8);

        // Convertir en JSON
        JSONObject jsonObject = ParseurXML.parseXMLFileContent(xmlContent);

        // Construire le Plan à partir du JSON
        Data.planVille = PlanFactory.creerPlan(jsonObject);
    }
}
