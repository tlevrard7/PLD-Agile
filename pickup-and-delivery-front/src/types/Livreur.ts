import { Livraison } from "@/types/Livraison";

export interface Livreur {
  id: number;
  nom: string;
  prenom: string;
  disponible: boolean;
  livraisons: Livraison[];
}