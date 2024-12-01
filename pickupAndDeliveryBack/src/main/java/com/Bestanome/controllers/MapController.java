package com.Bestanome.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Bestanome.dto.PointDTO;
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
}