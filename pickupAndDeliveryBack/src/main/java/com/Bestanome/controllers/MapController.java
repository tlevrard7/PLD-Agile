package com.Bestanome.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Bestanome.Model.Objets.Plan.Plan;
import com.Bestanome.Model.dto.PointDTO;
import com.Bestanome.services.MapService;

@RestController
@RequestMapping("/map")
public class MapController {

    private MapService mapService;

    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping("/random-point")
    public ResponseEntity<PointDTO> getRandomPoint() throws InterruptedException {
        return ResponseEntity.ok(mapService.getRandomPoint());
    }

    @PostMapping("/upload-xml")
    public ResponseEntity<Plan> uploadMap(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    
        try {
            Plan plan = mapService.parseMapFile(file);
            return ResponseEntity.ok(plan); // Retourne l'objet Plan en JSON
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
}