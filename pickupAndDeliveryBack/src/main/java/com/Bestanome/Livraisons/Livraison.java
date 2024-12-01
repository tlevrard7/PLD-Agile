package com.Bestanome.Livraisons;

public class Livraison {
  private Long pickup;
  private Long destination;
  private int dureeEnlevement;
  private int dureeLivraison;

  public Livraison(Long idStart, Long idEnd, int dEnlevement, int dLivraison){
    this.pickup = idStart;
    this.destination = idEnd;
    this.dureeEnlevement = dEnlevement;
    this.dureeLivraison = dLivraison;
  }

  public Long[] itineraire(){
    return new Long[] {this.pickup, this.destination};
  }
}
