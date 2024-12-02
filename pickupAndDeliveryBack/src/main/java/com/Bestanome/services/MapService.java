package com.Bestanome.services;

import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Bestanome.Outils.ParseurXML;
import com.Bestanome.Plan.Point;
import com.Bestanome.Plan.TypePoint;
import com.Bestanome.dto.PointDTO;

@Service
public class MapService {

    // @RequestMapping("/")
    String home() {
        return ParseurXML.parseXMLFile("../fichiersXMLPickupDelivery/myDeliverRequest.xml").toString(4);
        //return "Hello World!";
    }

    public PointDTO getRandomPoint() throws InterruptedException{
        Random rand = new Random();
        Point point = new Point(0, rand.nextDouble(-90, 90), rand.nextDouble(-180, 180), TypePoint.INTERSECTION);
        Thread.sleep(500); // Hardcoded delay to simulate compute time

        return PointDTO.fromPoint(point);
    }

    public PlanDTO chargerPlan(String pathFichier){
        JSONObject planSchemaJO = ParseurXML.parseXMLFile(pathFichier).getJSONObject("reseau");
        this.planVille = PlanFactory.creerPlan(planSchemaJO);
        return PlanDTO.fromPlan(this.planVille);
  }
}
