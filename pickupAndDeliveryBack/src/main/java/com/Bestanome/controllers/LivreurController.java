package com.Bestanome.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Bestanome.Model.Data;
import com.Bestanome.Model.Objets.Livraisons.Livraison;
import com.Bestanome.Model.Objets.Livraisons.Livreur;
import com.Bestanome.Model.dto.CircuitDTO;
import com.Bestanome.Model.dto.LivreurDTO;
import com.Bestanome.Model.dto.LivreurTourneeDTO;
import com.Bestanome.services.LivraisonService;
import com.Bestanome.services.LivreurService;

@RestController
@RequestMapping("/livreurs")
public class LivreurController {

    private static final Logger logger = LoggerFactory.getLogger(LivreurController.class);

    // GET mapping pour récupérer tous les livreurs
    @GetMapping
    public ResponseEntity<List<LivreurDTO>> getAllLivreurs() {
        return ResponseEntity.ok(LivreurDTO.fromListeLivreurs(LivreurService.getAll()));
    }

    // POST mapping pour assigner une livraison à un livreur
    @PostMapping("/assign")
    public ResponseEntity<String> assignDelivery(@RequestParam Long livreurId, @RequestParam Long pickup, @RequestParam Long destination) {

        // Rechercher le livreur
        Livreur livreur = LivreurService.getLivreur(livreurId);

        if (livreur == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livreur non trouvé");
        }

        // Rechercher la livraison
        Livraison livraison = LivraisonService.getDue(pickup, destination);

        if (livraison == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livraison non trouvée");
        }

        // Assigner la livraison au livreur
        livreur.assignerLivraison(livraison);

        return ResponseEntity.ok("Livraison assignée avec succès");
    }

    @GetMapping("/{id}/tournee")
    public ResponseEntity<?> getTournee(@PathVariable Long id) {
        // Trouver le livreur par son ID
        Livreur livreur = LivreurService.getLivreur(id);

        if (livreur == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livreur non trouvé.");
        }

        if (livreur.getLivraisonsAssignees().isEmpty()) {
            return ResponseEntity.badRequest().body("Aucune livraison assignée pour ce livreur.");
        }

        try {
            return ResponseEntity.ok(CircuitDTO.fromCircuit(LivreurService.findCircuit(livreur)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors du calcul du circuit : " + e.getMessage());
        }
    }

    @GetMapping("/export-all-tournees")
    public ResponseEntity<List<LivreurTourneeDTO>> exportAllTournees() {
        return ResponseEntity.ok(LivreurService.exportAllTournees());
    }

    @DeleteMapping("/unassign")
    public ResponseEntity<String> unassignDelivery(@RequestParam Long livreurId, @RequestParam Long pickup,
            @RequestParam Long destination) {
        logger.info("Unassign request received for Livreur ID: {}, Pickup: {}, Destination: {}", livreurId, pickup,
                destination);

        // Rechercher le livreur
        Livreur livreur = LivreurService.getLivreur(livreurId);

        if (livreur == null) {
            logger.error("Livreur non trouvé pour ID: {}", livreurId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livreur non trouvé");
        }

        // Rechercher la livraison parmi celles assignées au livreur
        Livraison livraisonToRemove = livreur.getLivraisonsAssignees().stream()
                .filter(l -> l.getPickup().equals(pickup) && l.getDestination().equals(destination))
                .findFirst()
                .orElse(null);

        if (livraisonToRemove == null) {
            logger.error("Livraison non trouvée pour Pickup: {} et Destination: {}", pickup, destination);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Livraison non trouvée parmi les livraisons assignées au livreur");
        }

        logger.info("Livraison trouvée, en cours de désassignation...");

        // Désassigner la livraison
        livreur.getLivraisonsAssignees().remove(livraisonToRemove);

        // Ajouter la livraison de retour à la liste des livraisons disponibles
        Data.getLivraisonsDues().add(livraisonToRemove);

        logger.info("Livraison désassignée avec succès");

        return ResponseEntity.ok("Livraison désassignée avec succès");
    }
}