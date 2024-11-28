package com.Bestanome.Livraisons;

public class Livraison {
  private Long pickup;
  private Long destination;

  public Livraison(Long idStart, Long idEnd){
    this.pickup = idStart;
    this.destination = idEnd;
  }

  public Long[] itineraire(){
    return new Long[] {this.pickup, this.destination};
  }
}
