package com.Bestanome.dto;

import java.io.Serializable;


import com.Bestanome.Livraisons.Circuit;

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
