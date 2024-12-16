package com.Bestanome.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Bestanome.Model.Data;
import com.Bestanome.Model.Objets.Livraisons.Livraison;
import com.Bestanome.Model.dto.LivraisonsEntrepotDTO;
import com.Bestanome.services.TourneeService;

@RestController
@RequestMapping("/delivery")
public class TourneeController {

    // Endpoint pour charger des livraisons depuis un fichier XML
    @PostMapping("/upload-deliveries")
    public ResponseEntity<LivraisonsEntrepotDTO> uploadDeliveries(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        try {
            TourneeService.chargerLivraisons(file);
            return ResponseEntity
                    .ok(LivraisonsEntrepotDTO.fromLivraisonsEntrepot(Data.livraisonsDues, Data.idEntrepot));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/update-pickup")
    public ResponseEntity<String> updatePickup(@RequestParam Long oldPickup, @RequestParam Long newPickup) {
        System.out
                .println("Requête reçue pour update-pickup : oldPickup = " + oldPickup + ", newPickup = " + newPickup);
        System.out.println("Livraisons disponibles : ");
        Data.getLivraisonsDues()
                .forEach(l -> System.out.println("Pickup: " + l.getPickup() + ", Destination: " + l.getDestination()));

        Livraison livraison = Data.getLivraisonsDues().stream()
                .filter(l -> l.getPickup().equals(oldPickup))
                .findFirst()
                .orElse(null);

        if (livraison == null) {
            System.out.println("Livraison non trouvée pour le pickup : " + oldPickup);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Livraison non trouvée pour le pickup donné.");
        }

        livraison.setPickup(newPickup);
        System.out.println("Pickup mis à jour avec succès : " + newPickup);
        return ResponseEntity.ok("Pickup de la livraison mis à jour avec succès.");
    }

    @PostMapping("/update-delivery")
    public ResponseEntity<String> updateDelivery(@RequestParam Long oldDestination, @RequestParam Long newDestination) {
        Livraison livraison = Data.getLivraisonsDues().stream()
                .filter(l -> l.getDestination().equals(oldDestination))
                .findFirst()
                .orElse(null);

        if (livraison == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Livraison non trouvée pour la destination donnée.");
        }

        livraison.setDestination(newDestination);
        return ResponseEntity.ok("Livraison mise à jour avec succès.");
    }
}