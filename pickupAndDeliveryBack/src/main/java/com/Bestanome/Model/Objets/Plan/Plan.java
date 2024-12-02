package com.Bestanome.Plan;

import java.util.ArrayList;
import com.Bestanome.Plan.Point;
import com.Bestanome.Plan.Segment;

public class Plan {
  // Atrributs
  private ArrayList<Point> points;
  private ArrayList<Segment> segments;

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

  public ArrayList<Point> points(){
    return this.points;
  }

  public ArrayList<Segment> segments(){
    return this.segments;
  }
}
