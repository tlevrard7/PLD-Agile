package com.Bestanome.Model.dto;

import java.io.Serializable;
import java.util.ArrayList;

import com.Bestanome.Model.Objets.Plan.Segment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SegmentDTO implements Serializable {
    private String nom;
    private Double longueur;
    private Long origine;
    private Long destination;

    public static ArrayList<SegmentDTO> fromListeSegments(ArrayList<Segment> segments) {
        ArrayList<SegmentDTO> segmentsDTO = new ArrayList<SegmentDTO>();
        for(Segment s : segments){
            segmentsDTO.add(SegmentDTO.fromSegment(s));
        }
        return segmentsDTO;
    }

    public static SegmentDTO fromSegment(Segment Segment) {
        return new SegmentDTO(Segment.getNomRue(), Segment.getLongueur(), Segment.getOrigine(), Segment.getDestination());
    }
}
