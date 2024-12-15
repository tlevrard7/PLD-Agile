import { Button, message } from "antd";
import LivreurService from "@/services/livreur-service";

export default function XmlExports() {
  const handleExportTournees = async () => {
    try {
      // Appel de l'API pour récupérer le fichier d'exportation
      const fileBlob = await LivreurService.exportAllTournees();

      // Créer un lien pour télécharger le fichier
      const url = window.URL.createObjectURL(fileBlob);
      const link = document.createElement("a");
      link.href = url;
      link.download = "export_tournees.json"; // Nom du fichier à télécharger
      document.body.appendChild(link);
      link.click();

      // Nettoyer le lien après le téléchargement
      document.body.removeChild(link);
      window.URL.revokeObjectURL(url);
      
      message.success("Exportation des tournées réussie !");
    } catch (error) {
      console.error("Erreur lors de l'exportation :", error);
      message.error("Erreur lors de l'exportation des tournées.");
    }
  };

  return (
    <div>
      <Button type="primary" className="w-full" onClick={handleExportTournees}>
        Exporter les tournées
      </Button>
    </div>
  );
}