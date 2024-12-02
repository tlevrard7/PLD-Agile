package com.Bestanome.Model.dto;

import java.io.Serializable;
import java.util.ArrayList;

import com.Bestanome.Model.Objets.Livraisons.Circuit;

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

    public static CircuitDTO fromCircuit(Circuit Circuit) {
        return new CircuitDTO(Circuit.segments);
    }
}
