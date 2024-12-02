package com.Bestanome.Model.Objets.Plan;

public class Point {
  // Atrributs
  private Long id;
  private Double longitude;
  private Double latitude;
  private TypePoint type;

  public Point(long id, Double lat, Double lng, TypePoint t){
    this.longitude = lng;
    this.latitude = lat;
    this.type = t;
    this.id = id;
  }

  public TypePoint type(){
    return this.type;
  }

  public Double[] coords(){
    return new Double[] {this.latitude, this.longitude};
  }

  public Long id(){
    return this.id;
  }
}
