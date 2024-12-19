package com.Bestanome.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Bestanome.Model.Objets.Livraisons.Livraison;
import com.Bestanome.Model.dto.LivraisonsEntrepotDOT;
import com.Bestanome.services.LivraisonService;

@RestController
@RequestMapping("/delivery")
public class LivraisonController {

    // Endpoint pour charger des livraisons depuis un fichier XML
    @PostMapping("/upload-deliveries")
    public ResponseEntity<LivraisonsEntrepotDOT> uploadDeliveries(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        try {
            LivraisonService.chargerLivraisons(file);
            return ResponseEntity.ok(LivraisonsEntrepotDOT.fromLivraisonsEntrepot(LivraisonService.getAllDues(), LivraisonService.getEntrepot()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/update-pickup")
    public ResponseEntity<String> updatePickup(@RequestParam Long oldPickup, @RequestParam Long newPickup, @RequestParam Long destination) {
        System.out.println("Requête reçue pour update-pickup : oldPickup = " + oldPickup + ", newPickup = " + newPickup);
        System.out.println("Livraisons disponibles : ");
        for (var livraison : LivraisonService.getAllDues())
            System.out.println("Pickup: " + livraison.getPickup() + ", Destination: " + livraison.getDestination());

        Livraison livraison = LivraisonService.getDue(oldPickup, destination);

        if (livraison == null) {
            System.out.println("Livraison non trouvée pour le pickup : " + oldPickup);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livraison non trouvée pour le pickup donné.");
        }

        livraison.setPickup(newPickup);
        System.out.println("Pickup mis à jour avec succès : " + newPickup);
        return ResponseEntity.ok("Pickup de la livraison mis à jour avec succès.");
    }

    @PostMapping("/update-delivery")
    public ResponseEntity<String> updateDelivery(@RequestParam Long oldDestination, @RequestParam Long newDestination, @RequestParam Long pickup) {
        Livraison livraison = LivraisonService.getDue(pickup, oldDestination);

        if (livraison == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livraison non trouvée pour la destination donnée.");
        }

        livraison.setDestination(newDestination);
        return ResponseEntity.ok("Livraison mise à jour avec succès.");
    }
}
