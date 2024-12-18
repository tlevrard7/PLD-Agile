package com.Bestanome.services;

import java.util.List;
import java.util.stream.Collectors;

import com.Bestanome.Model.Data;
import com.Bestanome.Model.Objets.Livraisons.Circuit;
import com.Bestanome.Model.Objets.Livraisons.Livreur;
import com.Bestanome.Model.Objets.Livraisons.Tournee;
import com.Bestanome.Model.Outils.TSP.TSPRunner;
import com.Bestanome.Model.dto.CircuitDTO;
import com.Bestanome.Model.dto.LivreurTourneeDTO;

public class LivreurService {
    public static Livreur getLivreur(Long id) {
        for (var livreur : Data.getLivreurs()) {
            if (livreur.getId() == id)
                return livreur;
        }
        return null;
    }

    public static Circuit findCircuit(Livreur livreur) {
        Tournee tournee = new Tournee();
        livreur.getLivraisonsAssignees().forEach(tournee::ajouterLivraison);
        return TSPRunner.findCircuit(tournee);
    }

    public static List<LivreurTourneeDTO> exportAllTournees() {
        List<LivreurTourneeDTO> tournees = Data.getLivreurs().stream()
            .filter(livreur -> !livreur.getLivraisonsAssignees().isEmpty())
            .map(livreur -> {
                try {
                    Tournee tournee = new Tournee();
                    livreur.getLivraisonsAssignees().forEach(tournee::ajouterLivraison);

                    Circuit circuit = TSPRunner.findCircuit(tournee);
                    return new LivreurTourneeDTO(livreur.getNom(), livreur.getPrenom(),
                            CircuitDTO.fromCircuit(circuit));
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            })
            .filter(dto -> dto != null)
            .collect(Collectors.toList());

        return tournees;
    }

    public static List<Livreur> getAll() {
        return Data.getLivreurs();
    }
}
