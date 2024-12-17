export interface LivraisonsEntrepot {
  livraisons: Livraison[];
  idEntrepot: number
}

export interface Livraison {
  id: number;
  pickup: number;
  destination: number;
  dureeEnlevement: number;
  dureeLivraison: number;
}