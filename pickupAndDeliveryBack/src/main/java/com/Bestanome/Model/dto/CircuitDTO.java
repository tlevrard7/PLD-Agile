package com.Bestanome.Model.dto;

import java.io.Serializable;
import java.util.ArrayList;

import com.Bestanome.Model.Objets.Livraisons.Circuit;
import com.Bestanome.Model.dto.SegmentDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CircuitDTO implements Serializable {
    private ArrayList<SegmentDTO> segments;
    private Double longueur;

    public static CircuitDTO fromCircuit(Circuit circuit) {
        return new CircuitDTO(SegmentDTO.fromListeSegments(circuit.getSegments()), circuit.getLongueur());
    }
}
