package com.Bestanome.Model.Objets.Livraisons;

import java.util.ArrayList;

import com.Bestanome.Model.Objets.Plan.Segment;

public class Circuit {
  // Atrributs
  private ArrayList<Segment> segments;
  private Double longueur;

  public Circuit() {
    this.segments = new ArrayList<Segment>();
    this.longueur = 0d;
  }

  public void ajouterSegment(Segment segment) {
    this.segments.add(segment);
    this.longueur += segment.getLongueur();
  }

  public ArrayList<Segment> getSegments() {
    return this.segments;
  }

  public Double getLongueur() {
    return this.longueur;
  }
}