package com.Bestanome.Model.dto;

import java.io.Serializable;

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

    public static LivraisonDTO fromLivraison(Livraison Livraison) {
        return new LivraisonDTO(Livraison.getItineraire()[0], Livraison.getItineraire()[1], Livraison.getDureeEnlevement(), Livraison.getDureeLivraison());
    }
}
