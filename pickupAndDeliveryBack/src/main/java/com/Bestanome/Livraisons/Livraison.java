package com.Bestanome.Livraisons;

import com.Bestanome.Plan.Point;

public class Livraison {
  private Point pickup;
  private Point destination;

  public Livraison(Point start, Point end){
    this.pickup = start;
    this.destination = end;
  }

  public Point[] itineraire(){
    return new Point[] {this.pickup, this.destination};
  }
}
