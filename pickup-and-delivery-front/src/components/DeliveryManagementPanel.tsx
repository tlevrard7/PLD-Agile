import { Livraison } from "@/types/Livraison";

interface DeliveryManagementPanelProps {
  livraisons: Livraison[] | null | undefined;
}

export default function DeliveryManagementPanel({ livraisons }: DeliveryManagementPanelProps) {
  return (
    <div className="p-4 grow">
      <h2 className="text-xl font-bold mb-4 text-black">Livraisons à assigner</h2>
      {livraisons.length === 0 ? (
        <p className="text-gray-500">Aucune livraison à afficher. Importez une demande de livraisons.</p>
      ) : (
        <table className="w-full border-collapse border">
          <thead>
            <tr>
              <th className="border p-2 text-black">Pickup</th>
              <th className="border p-2 text-black">Destination</th>
              <th className="border p-2 text-black">Durée Enlèvement (s)</th>
              <th className="border p-2 text-black">Durée Livraison (s)</th>
            </tr>
          </thead>
          <tbody>
            {livraisons.map((livraison, index) => (
              <tr key={index}>
                <td className="border p-2 text-black">{livraison.pickup}</td>
                <td className="border p-2 text-black">{livraison.destination}</td>
                <td className="border p-2 text-black">{livraison.dureeEnlevement}</td>
                <td className="border p-2 text-black">{livraison.dureeLivraison}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}