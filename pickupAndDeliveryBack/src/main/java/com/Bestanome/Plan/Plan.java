package com.Bestanome.Plan;

import java.util.ArrayList;
import java.util.List;
import com.Bestanome.Plan.Point;
import com.Bestanome.Plan.Segment;

public class Plan {
  // Atrributs
  private List<Point> points;
  private List<Segment> segments;

  public Plan(){
    this.points = new ArrayList<Point>();
    this.segments = new ArrayList<Segment>();
  }

  public void ajouterSegment(Segment segment){
    this.segments.add(segment);
  }

  public void ajouterPoint(Point point){
    points.add(point);
  }

  public List<Point> points(){
    return this.points;
  }

  public List<Segment> segments(){
    return this.segments;
  }
}
