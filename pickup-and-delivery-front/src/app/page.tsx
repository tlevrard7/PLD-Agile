"use client";

import { useState } from "react";
import Header from "@/components/Header";
import MapPlaceholder from "@/components/MapPlaceholder";
import XmlImports from "@/components/XmlImports";
import DeliveryManagementPanel from "@/components/DeliveryManagementPanel";
import LivreurPanel from "@/components/LivreurPanel";
import { Plan } from "@/types/Plan";
import { Livraison } from "@/types/Livraison";
import { Segment } from "@/types/Plan";
import { Livreur } from "@/types/Livreur";
import XmlExports from "@/components/XmlExports";

export default function Home() {
  const [plan, setPlan] = useState<Plan | null>(null);
  const [livraisons, setLivraisons] = useState<Livraison[]>([]);
  const [entrepot, setEntrepot] = useState<number | null>(null);
  const [circuit, setCircuit] = useState<{ segments: Segment[] } | null>(null);
  const [assignedDeliveries, setAssignedDeliveries] = useState<Livraison[]>([]);
  const [livreurs, setLivreurs] = useState<Livreur[]>([]);
  const [selectedLivraison, setSelectedLivraison] = useState<Livraison | null>(null);
  const [livreurInfos, setLivreurInfos] = useState<{
    [key: number]: { distance: number; tempsTotal: number };
  }>({});

  // Fonction pour mettre à jour le pickup
  const handleUpdatePickup = (updatedLivraison: Livraison) => {
    setLivraisons((prevLivraisons) =>
      prevLivraisons.map((livraison) =>
        livraison.id === updatedLivraison.id
          ? { ...livraison, pickup: updatedLivraison.pickup }
          : livraison
      )
    );
  };

  // Fonction pour mettre à jour le delivery
  const handleUpdateDelivery = (updatedLivraison: Livraison) => {
    setLivraisons((prevLivraisons) =>
      prevLivraisons.map((livraison) =>
        livraison.id === updatedLivraison.id
          ? { ...livraison, destination: updatedLivraison.destination }
          : livraison
      )
    );
  };

  // Fonction pour sélectionner une livraison
  const handleSelectLivraison = (livraison: Livraison) => {
    console.log("Livraison sélectionnée :", livraison);
    setSelectedLivraison(livraison);
  };

  return (
    <div className="flex flex-col h-screen overflow-hidden">
      <Header />

      <div className="flex flex-1 overflow-hidden">
        {/* Section Carte à gauche */}
        <div className="flex-1 bg-gray-200">
          <MapPlaceholder
            plan={plan}
            livraisons={livraisons}
            assignedDeliveries={assignedDeliveries}
            entrepot={entrepot}
            circuit={circuit}
            selectedLivraison={selectedLivraison}
            setSelectedLivraison={setSelectedLivraison}
            onUpdatePickup={(updatedLivraison, index) =>
              handleUpdatePickup(updatedLivraison, index)
            }
            onUpdateDelivery={(updatedLivraison, index) =>
              handleUpdateDelivery(updatedLivraison, index)
            }
          />
        </div>

        {/* Sidebar et contenu dynamique à droite */}
        <div className="w-1/3 bg-blue-100 p-4 flex flex-col place-items-stretch space-y-4 overflow-y-auto">
          <XmlImports
            setPlan={setPlan}
            setLivraisons={setLivraisons}
            setEntrepot={setEntrepot}
            setAssignedDeliveries={setAssignedDeliveries}
            setCircuit={setCircuit}
            setLivreurs={setLivreurs}
            setLivreurInfos={setLivreurInfos}
          />
          <DeliveryManagementPanel
            livraisons={livraisons}
            assignedDeliveries={assignedDeliveries}
            setAssignedDeliveries={setAssignedDeliveries}
            entrepot={entrepot}
            onSelectLivraison={handleSelectLivraison}
          />
          <LivreurPanel
            setCircuit={setCircuit}
            livreurInfos={livreurInfos}
            setLivreurInfos={setLivreurInfos}
            setLivreurs={setLivreurs}
          />
          <XmlExports />
        </div>
      </div>
    </div>
  );
}
