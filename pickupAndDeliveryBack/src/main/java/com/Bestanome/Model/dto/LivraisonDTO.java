package com.Bestanome.Model.dto;

import java.io.Serializable;
import java.util.ArrayList;

import com.Bestanome.Model.Objets.Livraisons.Livraison;

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
public class LivraisonDTO implements Serializable {
    private Long pickup;
    private Long destination;
    private int dureeEnlevement;
    private int dureeLivraison;

    /**
     * Convertit une liste de livraisons en une liste de LivraisonDTO.
     *
     * @param livraisons La liste des objets Livraison à convertir.
     * @return Une liste de LivraisonDTO.
     */
    public static ArrayList<LivraisonDTO> fromListeLivraisons(ArrayList<Livraison> livraisons) {
        ArrayList<LivraisonDTO> livraisonsDTO = new ArrayList<>();
        for (Livraison l : livraisons) {
            livraisonsDTO.add(new LivraisonDTO(
                l.getPickup(),
                l.getDestination(),
                l.getDureeEnlevement(),
                l.getDureeLivraison()
            ));
        }
        return livraisonsDTO;
    }
}