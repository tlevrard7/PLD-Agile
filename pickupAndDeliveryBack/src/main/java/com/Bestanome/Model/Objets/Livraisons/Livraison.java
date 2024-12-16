package com.Bestanome.Model.Objets.Livraisons;

public class Livraison {
    private Long pickup;
    private Long destination;
    private int dureeEnlevement;
    private int dureeLivraison;

    // Constructeur
    public Livraison(Long pickup, Long destination, int dureeEnlevement, int dureeLivraison) {
        this.pickup = pickup;
        this.destination = destination;
        this.dureeEnlevement = dureeEnlevement;
        this.dureeLivraison = dureeLivraison;
    }

    // Getters
    public Long getPickup() {
        return pickup;
    }

    public Long getDestination() {
        return destination;
    }

    public int getDureeEnlevement() {
        return dureeEnlevement;
    }

    public int getDureeLivraison() {
        return dureeLivraison;
    }

    // Setters
    public void setPickup(Long pickup) {
        this.pickup = pickup;
    }

    public void setDestination(Long destination) {
        this.destination = destination;
    }

    public void setDureeEnlevement(int dureeEnlevement) {
        this.dureeEnlevement = dureeEnlevement;
    }

    public void setDureeLivraison(int dureeLivraison) {
        this.dureeLivraison = dureeLivraison;
    }


}