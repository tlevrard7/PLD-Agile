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
  livreurInfos: { [key: number]: { distance: number; duree: number } };
  setLivreurInfos: React.Dispatch<React.SetStateAction<{ [key: number]: { distance: number; duree: number } }>>;
  setLivreurs: React.Dispatch<React.SetStateAction<Livreur[]>>;
}

export default function LivreurPanel({
  setCircuit,
  livreurInfos,
  setLivreurInfos,
}: LivreurPanelProps) {
  const [livreurs, setLivreurs] = useState<Livreur[]>([]);
  const [isOpen, setIsOpen] = useState<boolean>(false);

  useEffect(() => {
    LivreurService.getAllLivreurs()
      .then((data) => setLivreurs(data))
      .catch((err) => {
        alert(`Erreur lors de la récupération des livreurs : ${err.message}`);
      });
  }, [setLivreurs]);

  const toggleDropdown = () => setIsOpen(!isOpen);

  // const calculateTotalDistance = (segments: Segment[]) => {
  //   if (!segments || segments.length === 0) return 0;
  //   return segments.reduce((total, segment) => total + segment.longueur, 0);
  // };

  const calculateDuration = (distance: number) => {
    const speed = (15 * 1000) / 3600; // 15 km/h en mètres par seconde
    return distance / speed; // Durée en secondes
  };

  const formatTime = (seconds: number) => {
    const hours = Math.floor(seconds / 3600);
    const minutes = Math.floor((seconds % 3600) / 60);
    const secs = Math.floor(seconds % 60);
    return `${hours}h ${minutes}m ${secs}s`;
  };

  const handleShowTournee = async (livreurId: number) => {
    try {
      const circuit = await LivreurService.getTournee(livreurId);
  
      // Si le circuit est vide, réinitialiser le circuit
      if (!circuit || circuit.segments.length === 0) {
        setCircuit(null);
        setLivreurInfos((prev) => ({
          ...prev,
          [livreurId]: { distance: 0, duree: 0 },
        }));
        return;
      }
  
      setCircuit(circuit);
  
      // Calculer et stocker la distance totale et la durée pour le livreur
      const totalDistance = circuit.longueur//calculateTotalDistance(circuit.segments);
      const duree = calculateDuration(totalDistance);
      setLivreurInfos((prev) => ({
        ...prev,
        [livreurId]: { distance: totalDistance, duree },
      }));
    } catch (err) {
      if (err.message.includes("500") || err.message.includes("400")) {
        alert("Aucune livraison assignée à ce livreur.");
      } else {
        alert(`Erreur lors de la récupération de la tournée : ${err.message}`);
      }
      setCircuit(null); // Réinitialiser le circuit en cas d'erreur
      setLivreurInfos((prev) => ({
        ...prev,
        [livreurId]: { distance: 0, duree: 0 },
      }));
    }
  };

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
          <h2 className="text-lg font-bold mb-2 text-blue-600 text-center">
            Tournées des Livreurs
          </h2>
          <div className="flex flex-wrap justify-center gap-4">
            {livreurs.map((livreur) => (
              <div
                key={livreur.id}
                className="p-4 bg-blue-100 border border-blue-400 rounded-md shadow-sm text-center w-48 hover:shadow-md transition-shadow duration-300"
              >
                <h3 className="text-md font-semibold text-gray-700 mb-2">
                  {livreur.nom} {livreur.prenom}
                </h3>
                <button
                  className="bg-green-500 text-white px-2 py-1 rounded hover:bg-green-600 transition duration-300 mb-2"
                  onClick={() => handleShowTournee(livreur.id)}
                >
                  Afficher tournée
                </button>
                {livreurInfos[livreur.id] && (
                  <>
                    <p className="text-gray-600 text-sm">
                      Distance totale :{" "}
                      {livreurInfos[livreur.id].distance.toFixed(2)} m
                    </p>
                    <p className="text-gray-600 text-sm">
                      Durée estimée :{" "}
                      {formatTime(livreurInfos[livreur.id].duree)}
                    </p>
                  </>
                )}
              </div>
            ))}
          </div>
        </div>
      )}
    </div>
  );
}
