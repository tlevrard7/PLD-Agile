import InteractiveMap, { InteractiveMapProps } from "@/components/InteractiveMap";
import { Plan } from "@/types/Plan";
import { Livraison } from "@/types/Livraison";

export interface MapPlaceholderProps extends Omit<InteractiveMapProps, 'plan'> {
  plan: Plan | null;
  assignedDeliveries: Livraison[];
  onUpdatePickup: (updatedLivraison: Livraison) => void;
  onUpdateDelivery: (updatedLivraison: Livraison) => void;
}

export default function MapPlaceholder({
  plan,
  assignedDeliveries,
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