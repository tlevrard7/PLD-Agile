package com.Bestanome.Model.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.Bestanome.Model.Objets.Livraisons.Livraison;
import com.Bestanome.Model.Objets.Livraisons.Livreur;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LivreurDTO implements Serializable{
    private int id;
    private String nom;
    private String prenom;
    private int nombreDeLivraisons;
    private List<Livraison> livraisons; // Remplacer livraisonsIds par une liste de livraisons

    public static List<LivreurDTO> fromListeLivreurs(List<Livreur> livreurs) {
        return livreurs.stream().map(l -> new LivreurDTO(
            l.getId(),
            l.getNom(),
            l.getPrenom(),
            l.getNombreDeLivraisons(),
            l.getLivraisonsAssignees()
        )).collect(Collectors.toList());
    }
}