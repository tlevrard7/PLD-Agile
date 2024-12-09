import { Upload, Button, message } from "antd";
import { Plan } from "@/types/Plan";
import { Livraison } from "@/types/Livraison";
import TourneeService from "@/services/tournee-service";
import MapService from "@/services/map-service";

interface TestProps {
  setPlan: (plan: Plan) => void;
  setLivraisons: (livraisons: Livraison[]) => void;
}

export default function Test({ setPlan, setLivraisons }: TestProps) {
  const handleUploadMap = async (file: File) => {
    try {
      const uploadedPlan = await MapService.uploadMap(file);
      setPlan(uploadedPlan);
      message.success("Carte importée avec succès !");
    } catch (error) {
      console.error("Erreur lors de l'upload de la carte :", error);
      message.error("Erreur lors de l'importation de la carte.");
    }
  };

  const handleUploadLivraisons = async (file: File) => {
    try {
      const uploadedLivraisons = await TourneeService.uploadLivraisons(file);
      setLivraisons(uploadedLivraisons);
      message.success("Demandes de livraisons importées avec succès !");
    } catch (error) {
      console.error("Erreur lors de l'upload des livraisons :", error);
      message.error("Erreur lors de l'importation des livraisons.");
    }
  };

  return (
    <div className="flex flex-col items-center justify-center h-full space-y-4">
      <Upload
        beforeUpload={(file) => {
          handleUploadMap(file);
          return false;
        }}
        showUploadList={false}
      >
        <Button type="primary" className="bg-blue-500 hover:bg-blue-400 text-white border-none">
          Importer une carte
        </Button>
      </Upload>

      <Upload
        beforeUpload={(file) => {
          handleUploadLivraisons(file);
          return false;
        }}
        showUploadList={false}
      >
        <Button type="primary" className="bg-blue-500 hover:bg-blue-400 text-white border-none">
          Importer une demande de livraisons
        </Button>
      </Upload>
    </div>
  );
}