import { Upload, Button, message } from "antd";
import { Plan } from "@/types/Plan";
import { Livraison } from "@/types/Livraison";
import { Livreur } from "@/types/Livreur";
import TourneeService from "@/services/tournee-service";
import MapService from "@/services/map-service";
import { Dispatch, SetStateAction, useState } from "react";
import { v4 as uuidv4 } from 'uuid'; // Installer uuid : npm install uuid


interface XmlImportsProps {
  setPlan: Dispatch<SetStateAction<Plan | null>>;
  setLivraisons: Dispatch<SetStateAction<Livraison[]>>;
  setEntrepot: Dispatch<SetStateAction<number | null>>;
  setAssignedDeliveries: Dispatch<SetStateAction<Livraison[]>>;
  setCircuit: Dispatch<SetStateAction<{ segments: Segment[] } | null>>;
  setLivreurs: Dispatch<SetStateAction<Livreur[]>>;
  setLivreurInfos: Dispatch<
    SetStateAction<{ [key: number]: { distance: number; duree: number } }>
  >;
}

export default function XmlImports({
  setPlan,
  setLivraisons,
  setEntrepot,
  setAssignedDeliveries,
  setCircuit,
  setLivreurs,
  setLivreurInfos,
}: XmlImportsProps) {
  const [isMapUploaded, setIsMapUploaded] = useState(false);
  const [isDeliveryUploaded, setIsDeliveryUploaded] = useState(false);

  const handleUploadMap = async (file: File) => {
    try {
      setLivraisons([]);
      setEntrepot(null);
      setAssignedDeliveries([]);
      setCircuit(null);
      setLivreurs([]); // Réinitialise la liste des livreurs
      setLivreurInfos({}); // Réinitialise les informations des livreurs
      setIsDeliveryUploaded(false);
      
      const uploadedPlan = await MapService.uploadMap(file);
      setPlan(uploadedPlan);
      setIsMapUploaded(true);
      setIsDeliveryUploaded(false);
      message.success("Carte importée avec succès !");
    } catch (error) {
      console.error("Erreur lors de l'upload de la carte :", error);
      message.error("Erreur lors de l'importation de la carte.");
    }
  };

  const handleUploadLivraisons = async (file: File) => {
    try {
      const uploadedLivraisons = await TourneeService.uploadLivraisons(file);
      const livraisonsWithIds = uploadedLivraisons.livraisons.map((livraison) => ({
        ...livraison,
        id: uuidv4(), // Assigne un identifiant unique à chaque livraison
      }));
      setAssignedDeliveries([]);
      setCircuit(null);
      setLivreurInfos({}); // Réinitialise les informations des livreurs
      setLivraisons(livraisonsWithIds);
      setEntrepot(uploadedLivraisons.idEntrepot);
      message.success("Demandes de livraisons importées avec succès !");
    } catch (error) {
      console.error("Erreur lors de l'upload des livraisons :", error);
      message.error("Erreur lors de l'importation des livraisons.");
    }
  };

  return (
    <div className="flex flex-col space-y-4 place-items-stretch">
      <Upload
        className="upload-stretch"
        beforeUpload={(file) => {
          handleUploadMap(file);
          return false;
        }}
        showUploadList={false}
      >
        <Button type="primary" className="w-full">
          {isMapUploaded ? "Reinitialiser et importer une carte" : "Importer une carte"}
        </Button>
      </Upload>

      <Upload
        className="upload-stretch"
        beforeUpload={(file) => {
          handleUploadLivraisons(file);
          return false;
        }}
        showUploadList={false}
        disabled={!isMapUploaded || isDeliveryUploaded}
      >
        <Button
          type="primary"
          className="w-full"
          disabled={!isMapUploaded || isDeliveryUploaded}
        >
          Importer une demande de livraisons
        </Button>
      </Upload>
    </div>
  );
}
