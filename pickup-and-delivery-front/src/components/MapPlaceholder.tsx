import InteractiveMap from "@/components/InteractiveMap";
import { Plan } from "@/types/Plan";

export default function MapPlaceholder({ plan }: { plan: Plan | null }) {
  return (
    <div className="w-full h-full bg-gray-300 flex justify-center items-center">
      {plan ? (
        <InteractiveMap plan={plan} />
      ) : (
        <p className="text-lg font-bold text-gray-600">
          Importez une carte pour la visualiser
        </p>
      )}
    </div>
  );
}
