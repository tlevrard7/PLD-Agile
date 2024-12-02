package com.Bestanome.Model.dto;

import java.io.Serializable;
import java.util.ArrayList;

import com.Bestanome.Model.Objets.Plan.Plan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanDTO implements Serializable {
    private ArrayList<PointDTO> points;
    private ArrayList<SegmentDTO> segments;
    
    public static PlanDTO fromPlan(Plan Plan) {
        return new PlanDTO(PointDTO.fromListePoint(Plan.points), SegmentDTO.fromListeSegment(Plan.segments)); // Je pense qu'il faut faire un serialisateur ?
    }
}
