package com.Bestanome.Model.Objets.Livraisons;

import java.util.ArrayList;

import com.Bestanome.Model.Objets.Plan.Segment;

public class Circuit {
  // Atrributs
  private ArrayList<Segment> segments;

  public Circuit() {
    this.segments = new ArrayList<Segment>();
  }

  public void ajouterSegment(Segment segment) {
    this.segments.add(segment);
  }

  public ArrayList<Segment> getSegments() {
    return this.segments;
  }
}
