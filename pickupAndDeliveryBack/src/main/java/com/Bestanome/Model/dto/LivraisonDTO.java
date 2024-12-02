package com.Bestanome.dto;

import java.io.Serializable;

import com.Bestanome.Livraisons.Livraison;

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
        return new LivraisonDTO(Livraison.pickup,Livraison.destination,Livraison.dureeEnlevement,Livraison.dureeLivraison);
    }
}
