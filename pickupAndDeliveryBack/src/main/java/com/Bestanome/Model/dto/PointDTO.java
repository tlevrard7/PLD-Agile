package com.Bestanome.Model.dto;

import java.io.Serializable;
import java.util.ArrayList;

import com.Bestanome.Model.Objets.Plan.Point;
import com.Bestanome.Model.Objets.Plan.TypePoint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PointDTO implements Serializable {
    private Long id;
    private Double longitude;
    private Double latitude;
    private TypePoint type;

    public static PointDTO fromPoint(Point point) {
        return new PointDTO(
                point.getId(),
                point.getLongitude(),
                point.getLatitude(),
                point.getType());
    }

    public static ArrayList<PointDTO> fromListePoints(ArrayList<Point> points) {
        ArrayList<PointDTO> list = new ArrayList<PointDTO>();

        for (Point p : points) {
            list.add(PointDTO.fromPoint(p));
        }

        return list;
    }
}
