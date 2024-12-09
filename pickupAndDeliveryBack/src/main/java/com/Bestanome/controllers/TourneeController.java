package com.Bestanome.controllers;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Bestanome.Model.Data;
import com.Bestanome.Model.dto.LivraisonDTO;
import com.Bestanome.services.TourneeService;

@RestController
@RequestMapping("/delivery")
public class TourneeController {

    // Endpoint pour charger des livraisons depuis un fichier XML
    @PostMapping("/upload-livraisons")
    public ResponseEntity<ArrayList<LivraisonDTO>> uploadLivraisons(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        try {
            TourneeService.chargerLivraisons(file);
            return ResponseEntity.ok(LivraisonDTO.fromListeLivraisons(Data.livraisonsDues));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}