package com.Bestanome.Plan;

public class Segment {
  private String nom;
  private Double longueur;
  private Long debut;
  private Long fin;

  public Segment(String n, Double l, Long start, Long end){
    this.nom = n;
    this.longueur = l;
    this.debut = start;
    this.fin = end;
  }

  public String nom(){
    return this.nom;
  }
  public Double longueur(){
    return this.longueur;
  }
  public Long[] extremus(){
    return new Long[] {this.debut, this.fin};
  }

}
