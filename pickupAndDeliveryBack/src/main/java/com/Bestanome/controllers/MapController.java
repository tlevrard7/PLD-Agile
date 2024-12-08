package com.Bestanome.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Bestanome.Model.Data;
import com.Bestanome.Model.dto.LivraisonDTO;
import com.Bestanome.Model.dto.PlanDTO;
import com.Bestanome.Model.dto.PointDTO;
import com.Bestanome.services.MapService;

@RestController
@RequestMapping("/map")
public class MapController {

    // Endpoint pour obtenir un point aléatoire
    @GetMapping("/random-point")
    public ResponseEntity<PointDTO> getRandomPoint() throws InterruptedException {
        return ResponseEntity.ok(PointDTO.fromPoint(MapService.getRandomPoint()));
    }

    // Endpoint pour charger un plan depuis un fichier XML
    @PostMapping("/upload-xml")
    public ResponseEntity<PlanDTO> uploadMap(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        try {
            MapService.chargerPlan(file);
            return ResponseEntity.ok(PlanDTO.fromPlan(Data.planVille)); // Retourne l'objet Plan en JSON
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Endpoint pour charger des livraisons depuis un fichier XML
    @PostMapping("/upload-livraisons")
    public ResponseEntity<List<LivraisonDTO>> uploadLivraisons(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        try {
            MapService.chargerLivraisons(file);
            List<LivraisonDTO> livraisonsDTO = LivraisonDTO.fromListeLivraisons(Data.livraisonsDues);
            return ResponseEntity.ok(livraisonsDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}