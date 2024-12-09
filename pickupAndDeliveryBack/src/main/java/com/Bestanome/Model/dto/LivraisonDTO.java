package com.Bestanome.Model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.Bestanome.Model.Objets.Livraisons.Livraison;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    public static List<LivraisonDTO> fromListeLivraisons(List<Livraison> livraisons) {
        List<LivraisonDTO> livraisonsDTO = new ArrayList<>();
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