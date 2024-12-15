import {
  MapContainer,
  TileLayer,
  Polyline,
  Marker,
  MarkerProps,
} from "react-leaflet";
import "leaflet/dist/leaflet.css";
import { Plan, Segment } from "@/types/Plan";
import { useMemo, useState } from "react";
import { Livraison } from "@/types/Livraison";
import { LatLng, divIcon } from "leaflet";
import { getStableColor } from "@/utils/delivery-utils";

export interface InteractiveMapProps {
  plan: Plan;
  livraisons: Livraison[];
  entrepot: number | null;
  circuit: { segments: Segment[] } | null;
}

export default function InteractiveMap({
  plan,
  livraisons,
  entrepot,
  circuit,
}: InteractiveMapProps) {
  const [filter, setFilter] = useState<"all" | "aller" | "retour">("all");

  // Polylines pour le plan de base
  const polylines = useMemo(
    () =>
      plan.segments
        .map((segment) => {
          const origine = plan.points.find((p) => p.id === segment.origine);
          const destination = plan.points.find(
            (p) => p.id === segment.destination
          );
          return origine && destination
            ? [
                [origine.latitude, origine.longitude],
                [destination.latitude, destination.longitude],
              ]
            : null;
        })
        .filter(Boolean) as [number, number][][],
    [plan]
  );

  // Diviser le circuit en aller et retour
  const [allerPolylines, retourPolylines] = useMemo(() => {
    if (
      !circuit?.segments ||
      circuit.segments.length === 0 ||
      livraisons.length === 0
    ) {
      return [[], []];
    }

    const deliveryDestinations = new Set(
      livraisons.map((livraison) => livraison.destination)
    );

    // Trouver l'indice du dernier point de livraison dans le circuit
    let lastDeliveryIndex = -1;
    for (let i = 0; i < circuit.segments.length; i++) {
      if (deliveryDestinations.has(circuit.segments[i].destination)) {
        lastDeliveryIndex = i;
      }
    }

    let aller = [];
    let retour = [];

    for (let i = 0; i < circuit.segments.length; i++) {
      const segment = circuit.segments[i];
      const origine = plan.points.find((p) => p.id === segment.origine);
      const destination = plan.points.find((p) => p.id === segment.destination);

      if (origine && destination) {
        const polyline = [
          [origine.latitude, origine.longitude],
          [destination.latitude, destination.longitude],
        ];

        if (i <= lastDeliveryIndex) {
          aller.push(polyline);
          console.log(
            `Aller Segment: ${segment.nom} (${origine.id} -> ${destination.id})`
          );
        } else {
          retour.push(polyline);
          console.log(
            `Retour Segment: ${segment.nom} (${origine.id} -> ${destination.id})`
          );
        }
      }
    }

    console.log("Circuit complet :", circuit.segments);
    console.log("Circuit aller :", aller);
    console.log("Circuit retour :", retour);

    return [aller, retour];
  }, [circuit, livraisons, plan]);

  // Marqueurs pour les livraisons
  const livraisonMarkers = useMemo(() => {
    const markers: MarkerProps[] = [];
    for (let i = 0; i < livraisons.length; i++) {
      const livraison = livraisons[i];
      const pickup = plan.points.find((p) => p.id === livraison.pickup);
      const delivery = plan.points.find((p) => p.id === livraison.destination);
      if (pickup == null || delivery == null) continue;
      const color = getStableColor(livraison.pickup);

      const stylePickupColor = `background-color: ${color};`;

      const iconPickup = divIcon({
        html: `<span class="map-marker-pickup" style="${stylePickupColor}"/>`,
      });
      const iconDelivery = divIcon({
        html: `<span class="map-marker-delivery" style="${stylePickupColor}"/>`,
      });
      markers.push({
        position: new LatLng(pickup.latitude, pickup.longitude),
        icon: iconPickup,
      });
      markers.push({
        position: new LatLng(delivery.latitude, delivery.longitude),
        icon: iconDelivery,
      });
    }
    return markers;
  }, [livraisons, plan]);

  // Marqueur pour l'entrepôt
  const warehouseMarker: MarkerProps | null = useMemo(() => {
    if (entrepot == null) return null;
    const position = plan.points.find((p) => p.id === entrepot);
    if (position == null) return null;
    const iconWarehouse = divIcon({
      html: `<span class="map-marker-warehouse"/>`,
    });
    return {
      position: new LatLng(position.latitude, position.longitude),
      icon: iconWarehouse,
    };
  }, [entrepot, plan]);

  return (
    <div className="w-full h-full flex flex-col">
      <MapContainer
        center={[plan.points[0].latitude, plan.points[0].longitude]}
        zoom={13}
        style={{ flex: 1 }}
      >
        <TileLayer url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png" />

        {/* Polylines du plan de base */}
        {polylines.map((polyline, index) => (
          <Polyline key={index} positions={polyline} color="blue" />
        ))}

        {/* Circuit en fonction du filtre */}
        {filter === "all" &&
          circuit?.segments.map((segment, index) => {
            const origine = plan.points.find((p) => p.id === segment.origine);
            const destination = plan.points.find(
              (p) => p.id === segment.destination
            );
            return origine && destination ? (
              <Polyline
                key={`all-${index}`}
                positions={[
                  [origine.latitude, origine.longitude],
                  [destination.latitude, destination.longitude],
                ]}
                color="yellow"
                weight={4}
              />
            ) : null;
          })}

        {filter === "aller" &&
          allerPolylines.map((polyline, index) => (
            <Polyline
              key={`aller-${index}`}
              positions={polyline}
              color="red"
              weight={4}
            />
          ))}

        {filter === "retour" &&
          retourPolylines.map((polyline, index) => (
            <Polyline
              key={`retour-${index}`}
              positions={polyline}
              color="green"
              weight={4}
            />
          ))}

        {/* Marqueurs des livraisons */}
        {livraisonMarkers.map((props, index) => (
          <Marker {...props} key={index} />
        ))}

        {/* Marqueur de l'entrepôt */}
        {warehouseMarker && <Marker {...warehouseMarker} />}
      </MapContainer>

      {/* Légende et boutons */}
      <div className="bg-white p-2 shadow-md flex justify-center space-x-4">
        <button
          onClick={() => setFilter("all")}
          className="bg-yellow-500 text-white px-4 py-1 rounded"
        >
          Circuit
        </button>
        <button
          onClick={() => setFilter("aller")}
          className="bg-red-500 text-white px-4 py-1 rounded"
        >
          Aller
        </button>
        <button
          onClick={() => setFilter("retour")}
          className="bg-green-500 text-white px-4 py-1 rounded"
        >
          Retour
        </button>
      </div>
    </div>
  );
}
