package com.Bestanome.Model.Objets.Plan;

import java.util.ArrayList;

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
