import InteractiveMap, { InteractiveMapProps } from "@/components/InteractiveMap";
import { Plan } from "@/types/Plan";
import { Livraison } from "@/types/Livraison";

export interface MapPlaceholderProps extends Omit<InteractiveMapProps, 'plan'> {
  plan: Plan | null;
  assignedDeliveries: Livraison[];
  selectedLivraison: Livraison | null;
  setSelectedLivraison: (livraison: Livraison | null) => void;
  onUpdatePickup: (updatedLivraison: Livraison, index: number) => void;
  onUpdateDelivery: (updatedLivraison: Livraison, index: number) => void;
}

export default function MapPlaceholder({
  plan,
  assignedDeliveries,
  selectedLivraison,
  onUpdatePickup,
  onUpdateDelivery,
  ...props
}: MapPlaceholderProps) {
  return (
    <div className="w-full h-full bg-gray-300 flex justify-center items-center">
      {plan ? (
        <InteractiveMap
          plan={plan}
          assignedDeliveries={assignedDeliveries}
          selectedLivraison={selectedLivraison}
          onUpdatePickup={onUpdatePickup}
          onUpdateDelivery={onUpdateDelivery}
          {...props}
        />
      ) : (
        <p className="text-lg font-bold text-gray-600">
          Importez une carte pour la visualiser
        </p>
      )}
    </div>
  );
}