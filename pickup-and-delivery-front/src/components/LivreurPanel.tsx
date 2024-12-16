// LivreurPanel.tsx

import { useEffect, useState } from "react";
import LivreurService from "@/services/livreur-service";
import { Segment } from "@/types/Plan";

interface Livreur {
  id: number;
  nom: string;
  prenom: string;
}

interface Circuit {
  segments: Segment[];
}

interface LivreurPanelProps {
  setCircuit: (circuit: Circuit | null) => void;
}

export default function LivreurPanel({ setCircuit }: LivreurPanelProps) {
  const [livreurs, setLivreurs] = useState<Livreur[]>([]);
  const [error, setError] = useState<string | null>(null);
  const [isOpen, setIsOpen] = useState<boolean>(false);

  useEffect(() => {
    LivreurService.getAllLivreurs()
      .then(data => setLivreurs(data))
      .catch(err => setError(`Erreur lors de la récupération des livreurs : ${err.message}`));
  }, []);

  const toggleDropdown = () => setIsOpen(!isOpen);

  const handleShowTournee = async (livreurId: number) => {
    try {
      const circuit = await LivreurService.getTournee(livreurId);
      setCircuit(circuit);
    } catch (err) {
      if (err.message.includes("400")) {
        alert("Pas de livraison assignée à ce livreur.");
      } else {
        setError(`Erreur lors de la récupération de la tournée : ${err.message}`);
      }
    }
  };

  if (error) {
    return <p className="text-red-500 font-bold text-center mt-4">{error}</p>;
  }

  return (
    <div className="p-4 bg-white rounded-lg shadow-md w-full max-w-md mx-auto">
      <button
        className="w-full bg-blue-500 text-white py-2 px-4 rounded-md font-semibold hover:bg-blue-600 transition duration-300"
        onClick={toggleDropdown}
      >
        {isOpen ? "Masquer les Tournées" : "Afficher Tournées par Livreur"}
      </button>

      {isOpen && (
        <div className="mt-4 p-2 bg-blue-50 rounded-md shadow-inner">
          <h2 className="text-lg font-bold mb-2 text-blue-600 text-center">Tournées des Livreurs</h2>
          <div className="flex flex-wrap justify-center gap-2">
            {livreurs.map(livreur => (
              <div
                key={livreur.id}
                className="p-2 bg-blue-100 border border-blue-400 rounded-md shadow-sm text-center w-32 hover:shadow-md transition-shadow duration-300"
              >
                <h3 className="text-sm font-semibold text-gray-700 mb-2">
                  {livreur.nom} {livreur.prenom}
                </h3>
                <button
                  className="bg-green-500 text-white px-2 py-1 rounded hover:bg-green-600 transition duration-300"
                  onClick={() => handleShowTournee(livreur.id)}
                >
                  Afficher tournée
                </button>
              </div>
            ))}
          </div>
        </div>
      )}
    </div>
  );
}