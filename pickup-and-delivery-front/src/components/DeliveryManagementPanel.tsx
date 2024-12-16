"use client";

import { useEffect, useState } from "react";
import { Livraison } from "@/types/Livraison";
import { Livreur } from "@/types/Livreur";
import { getStableColor } from "@/utils/delivery-utils";
import LivreurService from "@/services/livreur-service";

interface DeliveryManagementPanelProps {
  livraisons: Livraison[];
  assignedDeliveries: Livraison[];
  setAssignedDeliveries: (deliveries: Livraison[]) => void;
  entrepot: number | null;
}

interface AssignedDelivery extends Livraison {
  livreur: Livreur;
}

export default function DeliveryManagementPanel({
  livraisons,
  assignedDeliveries,
  setAssignedDeliveries,
  entrepot,
}: DeliveryManagementPanelProps) {
  const [livreurs, setLivreurs] = useState<Livreur[]>([]);
  const [remainingDeliveries, setRemainingDeliveries] = useState<Livraison[]>(
    []
  );
  const [selectedLivreurId, setSelectedLivreurId] = useState<number | null>(
    null
  );
  const [error, setError] = useState<string | null>(null);

  // Initialiser les livraisons à assigner lorsque le composant est monté
  useEffect(() => {
    setRemainingDeliveries(
      livraisons
        .filter(
          (livraison) =>
            !assignedDeliveries.some(
              (assigned) => assigned.pickup === livraison.pickup
            )
        )
        .map((livraison) => ({ ...livraison, livreurId: null }))
    );
  }, [livraisons, assignedDeliveries]);

  // Charger les livreurs depuis l'API
  useEffect(() => {
    LivreurService.getAllLivreurs()
      .then((data) => setLivreurs(data))
      .catch((err) => setError(err.message));
  }, []);

  const handleLivreurChange = (index: number, livreurId: number) => {
    setRemainingDeliveries((prevDeliveries) => {
      const updatedDeliveries = [...prevDeliveries];
      updatedDeliveries[index] = { ...updatedDeliveries[index], livreurId };
      return updatedDeliveries;
    });
  };

  const handleAssignDelivery = async (livraison: Livraison) => {
    console.log("Assigning delivery with:", {
      livreurId: livraison.livreurId,
      pickup: livraison.pickup,
      destination: livraison.destination,
      dureeEnlevement: livraison.dureeEnlevement,
      dureeLivraison: livraison.dureeLivraison,
    });
  
    if (!livraison.livreurId) {
      alert("Veuillez sélectionner un livreur avant d'assigner une livraison.");
      return;
    }
  
    try {
      await LivreurService.assignDelivery(
        livraison.livreurId,
        livraison.pickup,
        livraison.destination
      );
  
      const livreur = livreurs.find((l) => l.id === livraison.livreurId);
      if (livreur) {
        setAssignedDeliveries((prev) => [...prev, { ...livraison, livreur }]);
  
        // Retirer uniquement la livraison spécifique qui a été assignée
        setRemainingDeliveries((prev) =>
          prev.filter(
            (l) =>
              l.pickup !== livraison.pickup ||
              l.destination !== livraison.destination ||
              l.dureeEnlevement !== livraison.dureeEnlevement ||
              l.dureeLivraison !== livraison.dureeLivraison
          )
        );
      }
    } catch (error) {
      alert(`Erreur lors de l'assignation : ${error.message}`);
    }
  };

  const handleUnassignDelivery = async (livraison: AssignedDelivery) => {
    try {
      await fetch(
        `http://localhost:8080/api/livreurs/unassign?livreurId=${livraison.livreur.id}&pickup=${livraison.pickup}&destination=${livraison.destination}`,
        {
          method: "DELETE",
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
  
      setAssignedDeliveries((prev) =>
        prev.filter((assigned) => assigned.pickup !== livraison.pickup)
      );
  
      setRemainingDeliveries((prev) => [
        ...prev,
        { ...livraison, livreurId: null },
      ]);
    } catch (error) {
      alert(`Erreur lors de la désassignation : ${error.message}`);
    }
  };

  if (error) {
    return (
      <p className="text-red-500 font-bold text-center mt-4">
        Erreur : {error}
      </p>
    );
  };

  return (
    <div className="p-4 grow flex gap-10 flex-col">
      {/* Section Adresse de l'entrepôt */}
      <div>
        <h2 className="text-xl font-bold mb-4 text-black text-center">
          Adresse de l'entrepôt
        </h2>
        <div className="flex gap-3">
          <span
            className="map-marker-warehouse"
            style={{ position: "unset" }}
          />
          <span className="text-black text-center">{entrepot ?? "Non défini"}</span>
        </div>
      </div>

      {/* Section Livraisons à assigner */}
      <div>
        <h2 className="text-xl font-bold mb-4 text-black text-center">
          Livraisons à assigner
        </h2>
        {remainingDeliveries.length === 0 ? (
          <p className="text-gray-500">
            Aucune livraison à afficher. Importez une demande de livraisons.
          </p>
        ) : (
          <table className="w-full border-collapse border">
            <thead>
              <tr>
                <th className="border p-2"></th>
                <th className="border p-2 text-black text-center">
                  <div className="flex gap-2 items-center">
                    <span
                      className="map-marker-pickup"
                      style={{ backgroundColor: "white", position: "unset" }}
                    />
                    Pickup
                  </div>
                </th>
                <th className="border p-2 text-black text-center">
                  <div className="flex gap-2 items-center">
                    <span
                      className="map-marker-delivery"
                      style={{ backgroundColor: "white", position: "unset" }}
                    />
                    Destination
                  </div>
                </th>
                <th className="border p-2 text-black text-center">Enlèvement (s)</th>
                <th className="border p-2 text-black text-center">Livraison (s)</th>
                <th className="border p-2 text-black text-center">Livreur</th>
                <th className="border p-2 text-black text-center">Actions</th>
              </tr>
            </thead>
            <tbody>
              {remainingDeliveries.map((livraison, index) => (
                <tr key={index}>
                  <td>
                    <span
                      className="map-marker-icon"
                      style={{
                        backgroundColor: getStableColor(livraison.pickup),
                        position: "unset",
                      }}
                    />
                  </td>
                  <td className="border p-2 text-black text-center">{livraison.pickup}</td>
                  <td className="border p-2 text-black text-center">
                    {livraison.destination}
                  </td>
                  <td className="border p-2 text-black text-center">
                    {livraison.dureeEnlevement}
                  </td>
                  <td className="border p-2 text-black text-center">
                    {livraison.dureeLivraison}
                  </td>

                  <td className="border p-2">
                    <div className="relative">
                      <select
                        className="appearance-none w-full bg-white border border-gray-300 p-2 rounded text-gray-700 cursor-pointer hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-blue-500"
                        value={livraison.livreurId || ""}
                        onChange={(e) =>
                          handleLivreurChange(index, Number(e.target.value))
                        }
                      >
                        <option value="" disabled>
                          Choisissez un livreur
                        </option>
                        {livreurs.map((livreur) => (
                          <option key={livreur.id} value={livreur.id}>
                            {livreur.nom} {livreur.prenom}
                          </option>
                        ))}
                      </select>
                    </div>
                  </td>

                  <td className="border p-2">
                    <button
                      className={`bg-blue-500 text-white px-2 py-1 rounded transition duration-300 ${
                        !livraison.livreurId
                          ? "opacity-50 cursor-not-allowed"
                          : "hover:bg-blue-600"
                      }`}
                      onClick={() => handleAssignDelivery(livraison)}
                      disabled={!livraison.livreurId}
                    >
                      Assigner
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>

      {/* Section Livraisons assignées */}
      <div>
        <h2 className="text-xl font-bold mb-4 text-black text-center">
          Livraisons assignées
        </h2>
        {assignedDeliveries.length === 0 ? (
          <p className="text-gray-500">Aucune livraison assignée.</p>
        ) : (
          <table className="w-full border-collapse border">
            <thead>
              <tr>
                <th className="border p-2"></th>
                <th className="border p-2 text-black text-center">
                  <div className="flex gap-2 items-center">
                    <span
                      className="map-marker-pickup"
                      style={{ backgroundColor: "white", position: "unset" }}
                    />
                    Pickup
                  </div>
                </th>
                <th className="border p-2 text-black text-center">
                  <div className="flex gap-2 items-center">
                    <span
                      className="map-marker-delivery"
                      style={{ backgroundColor: "white", position: "unset" }}
                    />
                    Destination
                  </div>
                </th>
                <th className="border p-2 text-black text-center">Enlèvement (s)</th>
                <th className="border p-2 text-black text-center">Livraison (s)</th>
                <th className="border p-2 text-black text-center">Livreur</th>
                <th className="border p-2 text-black text-center">Actions</th>
              </tr>
            </thead>
            <tbody>
              {assignedDeliveries.map((delivery, index) => (
                <tr key={index}>
                  <td>
                    <span
                      className="map-marker-icon"
                      style={{
                        backgroundColor: getStableColor(delivery.pickup),
                        position: "unset",
                      }}
                    />
                  </td>
                  <td className="border p-2 text-black text-center">{delivery.pickup}</td>
                  <td className="border p-2 text-black text-center">
                    {delivery.destination}
                  </td>
                  <td className="border p-2 text-black text-center">
                    {delivery.dureeEnlevement}
                  </td>
                  <td className="border p-2 text-black text-center">
                    {delivery.dureeLivraison}
                  </td>
                  <td className="border p-2 text-black text-center">
                    {delivery.livreur.nom} {delivery.livreur.prenom}
                  </td>
                  <td className="border p-2">
                    <button
                      className="bg-red-500 text-white px-2 py-1 rounded hover:bg-red-600"
                      onClick={() => handleUnassignDelivery(delivery)}
                    >
                      Désassigner
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
}
