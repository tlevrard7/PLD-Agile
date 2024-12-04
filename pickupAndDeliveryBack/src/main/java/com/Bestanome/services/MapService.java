package com.Bestanome.services;

import java.io.IOException;
import java.util.Random;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Bestanome.Model.Data;
import com.Bestanome.Model.Objets.Plan.PlanFactory;
import com.Bestanome.Model.Objets.Plan.Point;
import com.Bestanome.Model.Objets.Plan.TypePoint;
import com.Bestanome.Model.Outils.ParseurXML;

@Service
public class MapService {

    // @RequestMapping("/")
    // String home() {
    //     return ParseurXML.parseXMLFileContent("../fichiersXMLPickupDelivery/myDeliverRequest.xml").toString(4);
    //     // return "Hello World!";
    // }

    static public Point getRandomPoint() throws InterruptedException { // test function to remove
        Random rand = new Random();
        Point point = new Point(0L, rand.nextDouble(-90, 90), rand.nextDouble(-180, 180), TypePoint.INTERSECTION);
        Thread.sleep(500); // Hardcoded delay to simulate compute time

        return point;
    }

    static public void chargerPlan(MultipartFile file) throws IOException {
        // Convertir en JSON
        JSONObject planVilleJo = ParseurXML.parseXMLFileContent(file);

        // Construire le Plan à partir du JSON
        Data.planVille = PlanFactory.creerPlan(planVilleJo);
    }
}
