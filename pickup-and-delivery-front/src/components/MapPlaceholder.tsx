import InteractiveMap, { InteractiveMapProps } from "@/components/InteractiveMap";
import { Plan } from "@/types/Plan";

export interface MapPlaceholderProps extends Omit<InteractiveMapProps, 'plan'> {
  plan: Plan | null;
  onUpdateLivraison: (updatedLivraison: Livraison) => void;
}

export default function MapPlaceholder({ plan, onUpdateLivraison, ...props }: MapPlaceholderProps) {
  return (
    <div className="w-full h-full bg-gray-300 flex justify-center items-center">
      {plan ? (
        <InteractiveMap plan={plan} onUpdateLivraison={onUpdateLivraison} {...props} />
      ) : (
        <p className="text-lg font-bold text-gray-600">
          Importez une carte pour la visualiser
        </p>
      )}
    </div>
  );
}