package com.Bestanome.Model.dto;

import java.io.Serializable;
import java.util.ArrayList;

import com.Bestanome.Model.Objets.Livraisons.Livraison;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LivraisonsEntrepotDOT implements Serializable {
    private ArrayList<LivraisonDTO> livraisons;
    private Long idEntrepot;

    public static LivraisonsEntrepotDOT fromLivraisonsEntrepot(ArrayList<Livraison> livraisons, Long idEntrepot) {
        return new LivraisonsEntrepotDOT(
            LivraisonDTO.fromListeLivraisons(livraisons),
            idEntrepot);
    }
}
