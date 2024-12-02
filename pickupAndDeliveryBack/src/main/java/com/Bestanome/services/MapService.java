package com.Bestanome.services;

import java.util.Random;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.Bestanome.Model.Data;
import com.Bestanome.Model.Objets.Plan.PlanFactory;
import com.Bestanome.Model.Objets.Plan.Point;
import com.Bestanome.Model.Objets.Plan.TypePoint;
import com.Bestanome.Model.Outils.ParseurXML;
import com.Bestanome.Model.dto.PlanDTO;
import com.Bestanome.Model.dto.PointDTO;

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
        Data.planVille = PlanFactory.creerPlan(planSchemaJO);
        return PlanDTO.fromPlan(this.planVille);
  }
}
