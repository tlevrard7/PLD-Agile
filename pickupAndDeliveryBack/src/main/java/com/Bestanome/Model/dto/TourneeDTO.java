package com.Bestanome.Model.dto;

import java.io.Serializable;
import java.util.List;

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
    private List<LivraisonDTO> livraisons;
    private CircuitDTO circuit;

    public static TourneeDTO fromTournee(Tournee tournee) {
        return new TourneeDTO(
            LivraisonDTO.fromListeLivraisons(tournee.getLivraisons()),
            CircuitDTO.fromCircuit(tournee.getCircuit())
        );
    }
}