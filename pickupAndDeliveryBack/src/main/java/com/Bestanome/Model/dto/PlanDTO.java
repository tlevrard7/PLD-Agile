Planpackage com.Bestanome.dto;

import java.io.Serializable;

import com.Bestanome.Plan.Plan;

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
