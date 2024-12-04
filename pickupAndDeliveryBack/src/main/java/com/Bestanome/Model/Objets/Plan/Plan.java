package com.Bestanome.Model.Objets.Plan;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Plan {

    @JsonProperty
    private ArrayList<Point> points;

    @JsonProperty
    private ArrayList<Segment> segments;

    public Plan() {
        this.points = new ArrayList<>();
        this.segments = new ArrayList<>();
    }

    public void ajouterPoint(Point point) {
        this.points.add(point);
    }

    public void ajouterSegment(Segment segment) {
        this.segments.add(segment);
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    public ArrayList<Segment> getSegments() {
        return this.segments;
    }

    public void setSegments(ArrayList<Segment> segments) {
        this.segments = segments;
    }

}
