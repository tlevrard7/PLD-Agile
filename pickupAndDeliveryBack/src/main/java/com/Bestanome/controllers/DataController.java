package com.Bestanome.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Bestanome.services.DataService;

@RestController
@RequestMapping("/data")
public class DataController {

    @PostMapping("/reset")
    public ResponseEntity<String> resetData() {
        DataService.resetData();
        return ResponseEntity.ok("Données réinitialisées avec succès.");
    }
}