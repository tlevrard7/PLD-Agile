package com.Bestanome.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Bestanome.Model.dto.PlanDTO;
import com.Bestanome.services.MapService;

@RestController
@RequestMapping("/map")
public class MapController {

    // Endpoint pour charger un plan depuis un fichier XML
    @PostMapping("/upload-xml")
    public ResponseEntity<PlanDTO> uploadMap(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        try {
            // Charger le plan
            MapService.chargerPlan(file);

            return ResponseEntity.ok(PlanDTO.fromPlan(MapService.getPlan()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}