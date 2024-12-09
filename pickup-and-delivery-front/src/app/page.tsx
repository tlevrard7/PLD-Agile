"use client";

import { useState } from "react";
import Header from "@/components/Header";
import Footer from "@/components/Footer";
import Sidebar from "@/components/Sidebar";
import MapPlaceholder from "@/components/MapPlaceholder";
import Test from "@/pages/test";
import DeliveryManagementPanel from "@/components/DeliveryManagementPanel";
import { Plan } from "@/types/Plan";
import { Livraison } from "@/types/Livraison";

export default function Home() {
  const [selectedTab, setSelectedTab] = useState("settings");
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
        <div className="w-1/3 bg-blue-100 p-4 flex flex-col">
          <Sidebar onTabSelect={setSelectedTab} />
          <div className="flex-1 mt-4 overflow-y-auto">
            {selectedTab === "settings" && <Test setPlan={setPlan} setLivraisons={setLivraisons} />}
            {selectedTab === "deliveries" && <DeliveryManagementPanel livraisons={livraisons} />}
          </div>
        </div>
      </div>

      <Footer />
    </div>
  );
}