import { Button, message } from "antd";
import LivreurService from "@/services/livreur-service";

export default function XmlExports() {
  const handleExportTournees = async () => {
    try {
      const fileBlob = await LivreurService.exportAllTournees();
      downloadFile(fileBlob, "export_tournees.json", "Exportation des tournées réussie !");
    } catch (error) {
      handleError(error, "Erreur lors de l'exportation des tournées.");
    }
  };

  const handleExportAssignedDeliveries = async () => {
    try {
      // Appel de l'API pour récupérer le fichier d'exportation
      const fileBlob = await LivreurService.exportAssignedDeliveries();

      // Créer un lien pour télécharger le fichier
      const url = window.URL.createObjectURL(fileBlob);
      const link = document.createElement("a");
      link.href = url;
      link.download = "livraisons_assignees.json"; // Nom du fichier à télécharger
      document.body.appendChild(link);
      link.click();

      // Nettoyer le lien après le téléchargement
      document.body.removeChild(link);
      window.URL.revokeObjectURL(url);

      message.success("Exportation des livraisons assignées réussie !");
    } catch (error) {
      console.error("Erreur lors de l'exportation :", error);
      message.error("Erreur lors de l'exportation des livraisons assignées.");
    }
  };

  const downloadFile = (fileBlob: Blob, fileName: string, successMessage: string) => {
    const url = window.URL.createObjectURL(fileBlob);
    const link = document.createElement("a");
    link.href = url;
    link.download = fileName;
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
    window.URL.revokeObjectURL(url);
    message.success(successMessage);
  };

  const handleError = (error: any, errorMessage: string) => {
    console.error(errorMessage, error);
    message.error(errorMessage);
  };

  return (
    <div className="flex flex-col gap-4">
     <Button type="primary" className="w-full" onClick={handleExportTournees}>
        Exporter les tournées
      </Button>
    </div>
  );
}
