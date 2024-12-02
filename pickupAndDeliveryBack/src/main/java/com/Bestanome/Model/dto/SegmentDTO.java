package com.Bestanome.dto;

import java.io.Serializable;

import com.Bestanome.Plan.Segment;
import com.Bestanome.Plan.TypeSegment;

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
    private Long debut;
    private Long fin;

    public static ArrayList<SegmentDTO> fromListeSegments(ArrayList<Segment> segments) {
        ArrayList<SegmentDTO> segmentsDTO = new ArrayList<SegmentDTO>();
        for(Segment s : segments){
            segmentsDTO.add(SegmentDTO.fromSegment(s));
        }
        return segmentsDTO;
    }

    public static SegmentDTO fromSegment(Segment Segment) {
        return new SegmentDTO(Segment.nom(), Segment.longueur(), Segment.debut(), Segment.fin());
    }
}
