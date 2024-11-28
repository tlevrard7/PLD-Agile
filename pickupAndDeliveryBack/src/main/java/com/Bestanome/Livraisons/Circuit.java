package com.Bestanome.Livraisons;

import java.util.ArrayList;

import com.Bestanome.Plan.Segment;

public class Circuit {
  // Atrributs
  private ArrayList<Segment> segments;

  public Circuit(){
    this.segments = new ArrayList<Segment>();
  }

  public void ajouterSegment(Segment segment){
    this.segments.add(segment);
  }

  public ArrayList<Segment> segments(){
    return this.segments;
  }
}
