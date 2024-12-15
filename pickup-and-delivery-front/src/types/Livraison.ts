export interface LivraisonsEntrepot {
  livraisons: Livraison[];
  idEntrepot: number
}

export interface Livraison {
  pickup: number;
  destination: number;
  dureeEnlevement: number;
  dureeLivraison: number;
}