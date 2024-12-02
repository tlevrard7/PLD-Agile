package com.Bestanome.Model.dto;

import java.io.Serializable;
import java.util.ArrayList;

import com.Bestanome.Model.Objets.Livraisons.Tournee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TourneeDTO implements Serializable {
    private ArrayList<LivraisonDTO> livraisons;
    private CircuitDTO circuit;

    public static TourneeDTO fromTournee(Tournee Tournee) {
        return new TourneeDTO(Tournee.livraisons, Tournee.circuit);
    }
}
