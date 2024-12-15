import { Livreur } from "../types/Livreur";

const root = process.env.NEXT_PUBLIC_API_URL + '/livreurs';

export default class LivreurService {
  // Méthode pour récupérer tous les livreurs
  public static async getAllLivreurs(): Promise<Livreur[]> {
    return fetch(root)
      .then(response => {
        if (!response.ok) {
          throw new Error(`Erreur HTTP ${response.status}`);
        }
        return response.json();
      })
      .then(data => data.map((livreur: any) => ({
        ...livreur,
        livraisons: livreur.livraisons || [], // S'assurer que livraisons est un tableau par défaut
      })))
      .catch(error => {
        throw new Error(`Erreur lors de la récupération des livreurs : ${error.message}`);
      });
  }

  // Méthode pour assigner une livraison à un livreur
  public static async assignDelivery(livreurId: number, pickup: number, destination: number) {
    return fetch(`${root}/assign?livreurId=${livreurId}&pickup=${pickup}&destination=${destination}`, {
      method: "POST",
    }).then(response => {
      if (!response.ok) {
        throw new Error(`Erreur HTTP ${response.status}`);
      }
      return response.text();
    });
  }

  // Méthode pour récupérer la tournée d'un livreur
  public static async getTournee(livreurId: number) {
    return fetch(`${root}/${livreurId}/tournee`)
      .then(response => {
        if (!response.ok) {
          throw new Error(`Erreur HTTP ${response.status}`);
        }
        return response.json();
      })
      .catch(error => {
        throw new Error(`Erreur lors de la récupération de la tournée : ${error.message}`);
      });
  }

  // Méthode pour exporter toutes les tournées
  public static async exportAllTournees(): Promise<Blob> {
    const response = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/livreurs/export-all-tournees`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    });

    if (!response.ok) {
      throw new Error(`Erreur HTTP ${response.status}`);
    }

    return await response.blob(); // Retourne le fichier sous forme de Blob
  }
}