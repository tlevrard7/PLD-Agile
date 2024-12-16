"use client";

import { useEffect, useState } from "react";
import { Livraison } from "@/types/Livraison";
import { Livreur } from "@/types/Livreur";
import { getStableColor } from "@/utils/delivery-utils";
import LivreurService from "@/services/livreur-service";

interface DeliveryManagementPanelProps {
  livraisons: Livraison[];
  entrepot: number | null;
}

interface AssignedDelivery extends Livraison {
  livreur: Livreur;
}

export default function DeliveryManagementPanel({
  livraisons,
  entrepot,
}: DeliveryManagementPanelProps) {
  const [livreurs, setLivreurs] = useState<Livreur[]>([]);
  const [assignedDeliveries, setAssignedDeliveries] = useState<
    AssignedDelivery[]
  >([]);
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
      livraisons.filter(
        (livraison) =>
          !assignedDeliveries.some((assigned) => assigned.pickup === livraison.pickup)
      ).map((livraison) => ({ ...livraison, livreurId: null }))
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
        setAssignedDeliveries([
          ...assignedDeliveries,
          { ...livraison, livreur },
        ]);
        setRemainingDeliveries(
          remainingDeliveries.filter((l) => l.pickup !== livraison.pickup)
        );
      }
    } catch (error) {
      alert(`Erreur lors de l'assignation : ${error.message}`);
    }
  };

  if (error) {
    return (
      <p className="text-red-500 font-bold text-center mt-4">
        Erreur : {error}
      </p>
    );
  }

  return (
    <div className="p-4 grow flex gap-10 flex-col">
      {/* Section Adresse de l'entrepôt */}
      <div>
        <h2 className="text-xl font-bold mb-4 text-black">
          Adresse de l'entrepôt
        </h2>
        <div className="flex gap-3">
          <span
            className="map-marker-warehouse"
            style={{ position: "unset" }}
          />
          <span className="text-black">{entrepot ?? "Non défini"}</span>
        </div>
      </div>

      {/* Section Livraisons à assigner */}
      <div>
        <h2 className="text-xl font-bold mb-4 text-black">
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
                <th className="border p-2 text-black">
                  <div className="flex gap-2 items-center">
                    <span
                      className="map-marker-pickup"
                      style={{ backgroundColor: "white", position: "unset" }}
                    />
                    Pickup
                  </div>
                </th>
                <th className="border p-2 text-black">
                  <div className="flex gap-2 items-center">
                    <span
                      className="map-marker-delivery"
                      style={{ backgroundColor: "white", position: "unset" }}
                    />
                    Destination
                  </div>
                </th>
                <th className="border p-2 text-black">Enlèvement (s)</th>
                <th className="border p-2 text-black">Livraison (s)</th>
                <th className="border p-2 text-black">Livreur</th>
                <th className="border p-2 text-black">Actions</th>
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
                  <td className="border p-2 text-black">{livraison.pickup}</td>
                  <td className="border p-2 text-black">
                    {livraison.destination}
                  </td>
                  <td className="border p-2 text-black">
                    {livraison.dureeEnlevement}
                  </td>
                  <td className="border p-2 text-black">
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
                      className="bg-blue-500 text-white px-2 py-1 rounded hover:bg-blue-600 transition duration-300"
                      onClick={() => handleAssignDelivery(livraison)}
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
        <h2 className="text-xl font-bold mb-4 text-black">
          Livraisons assignées
        </h2>
        {assignedDeliveries.length === 0 ? (
          <p className="text-gray-500">Aucune livraison assignée.</p>
        ) : (
          <table className="w-full border-collapse border">
            <thead>
              <tr>
                <th className="border p-2"></th>
                <th className="border p-2 text-black">
                  <div className="flex gap-2 items-center">
                    <span
                      className="map-marker-pickup"
                      style={{ backgroundColor: "white", position: "unset" }}
                    />
                    Pickup
                  </div>
                </th>
                <th className="border p-2 text-black">
                  <div className="flex gap-2 items-center">
                    <span
                      className="map-marker-delivery"
                      style={{ backgroundColor: "white", position: "unset" }}
                    />
                    Destination
                  </div>
                </th>
                <th className="border p-2 text-black">Enlèvement (s)</th>
                <th className="border p-2 text-black">Livraison (s)</th>
                <th className="border p-2 text-black">Livreur</th>
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
                  <td className="border p-2 text-black">{delivery.pickup}</td>
                  <td className="border p-2 text-black">
                    {delivery.destination}
                  </td>
                  <td className="border p-2 text-black">
                    {delivery.dureeEnlevement}
                  </td>
                  <td className="border p-2 text-black">
                    {delivery.dureeLivraison}
                  </td>
                  <td className="border p-2 text-black">
                    {delivery.livreur.nom} {delivery.livreur.prenom}
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