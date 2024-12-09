"use client";

import { useState } from "react";
import Header from "@/components/Header";
import MapPlaceholder from "@/components/MapPlaceholder";
import XmlImports from "@/components/XmlImports";
import DeliveryManagementPanel from "@/components/DeliveryManagementPanel";
import { Plan } from "@/types/Plan";
import { Livraison } from "@/types/Livraison";
import { Button } from "antd";
import XmlExports from "@/components/XmlExports";

export default function Home() {
  const [plan, setPlan] = useState<Plan | null>(null);
  const [livraisons, setLivraisons] = useState<Livraison[]>([]);

  return (
    <div className="flex flex-col h-screen overflow-hidden">
      <Header />

      <div className="flex flex-1 overflow-hidden">
        {/* Section Carte à gauche */}
        <div className="flex-1 bg-gray-200">
          <MapPlaceholder plan={plan} />
        </div>

        {/* Sidebar et contenu dynamique à droite */}
        <div className="w-1/3 bg-blue-100 p-4 flex flex-col place-items-stretch">
          <XmlImports setPlan={setPlan} setLivraisons={setLivraisons} />
          <DeliveryManagementPanel livraisons={livraisons}/>
          <XmlExports/>
        </div>
      </div>

    </div>
  );
}