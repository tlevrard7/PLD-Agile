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
  onUpdatePickup: (updatedLivraison: Livraison) => void;
  onUpdateDelivery: (updatedLivraison: Livraison) => void;
}

export default function InteractiveMap({
  plan,
  livraisons,
  entrepot,
  circuit,
  onUpdatePickup,
  onUpdateDelivery,
}: InteractiveMapProps) {
  const [playIndex, setPlayIndex] = useState<number>(0);
  const [isPlaying, setIsPlaying] = useState<boolean>(false);

  // Fonction pour mettre à jour une livraison
  const handleDragEnd = async (index: number, newPosition: LatLng) => {
    // Log pour vérifier les points du plan
    console.log("Points du plan :", plan.points);

    const validPoints = plan.points.filter(
      (point) =>
        point.type === "DESTINATION" ||
        point.type === "PICKUP" ||
        point.type === "INTERSECTION"
    );

    // Log pour vérifier les points valides après le filtre
    console.log("Points valides pour le déplacement :", validPoints);

    if (validPoints.length === 0) {
      alert("Aucun point valide trouvé pour le déplacement.");
      return;
    }

    const nearestPoint = validPoints.reduce((closest, point) => {
      const distance = newPosition.distanceTo(
        new LatLng(point.latitude, point.longitude)
      );
      return distance <
        newPosition.distanceTo(new LatLng(closest.latitude, closest.longitude))
        ? point
        : closest;
    });

    console.log("Nearest point after drag:", nearestPoint);

    // Mettre à jour la livraison avec le nouvel identifiant de destination
    const oldDestination = livraisons[index].destination;
    const updatedLivraison = {
      ...livraisons[index],
      destination: nearestPoint.id,
    };

    try {
      // Appel API pour mettre à jour la livraison sur le backend
      const response = await fetch(
        `http://localhost:8080/api/delivery/update-delivery?oldDestination=${oldDestination}&newDestination=${nearestPoint.id}`,
        {
          method: "POST",
        }
      );

      if (!response.ok) {
        throw new Error(`Erreur HTTP ${response.status}`);
      }

      console.log("Livraison mise à jour avec succès.");

      // Mettre à jour l'état du frontend après la mise à jour réussie
      onUpdateDelivery(updatedLivraison);
    } catch (error) {
      console.error("Erreur lors de la mise à jour de la livraison :", error);
      alert(`Erreur lors de la mise à jour de la livraison : ${error.message}`);
    }
  };

  // Fonction pour mettre à jour le pickup
  const handlePickupDragEnd = async (index: number, newPosition: LatLng) => {
    console.log("Points du plan :", plan.points);
  
    const validPoints = plan.points.filter(
      (point) =>
        point.type === "DESTINATION" ||
        point.type === "PICKUP" ||
        point.type === "INTERSECTION"
    );
  
    console.log("Points valides pour le déplacement :", validPoints);
  
    if (validPoints.length === 0) {
      alert("Aucun point valide trouvé pour le déplacement.");
      return;
    }
  
    const nearestPoint = validPoints.reduce((closest, point) => {
      const distance = newPosition.distanceTo(
        new LatLng(point.latitude, point.longitude)
      );
      return distance <
        newPosition.distanceTo(new LatLng(closest.latitude, closest.longitude))
        ? point
        : closest;
    });
  
    console.log("Nearest point after drag:", nearestPoint);
  
    const oldPickup = livraisons[index].pickup;
    const updatedPickup = {
      ...livraisons[index],
      pickup: nearestPoint.id,
    };
  
    try {
      const response = await fetch(
        `http://localhost:8080/api/delivery/update-pickup?oldPickup=${oldPickup}&newPickup=${nearestPoint.id}`,
        {
          method: "POST",
        }
      );
    
      if (!response.ok) {
        throw new Error(`Erreur HTTP ${response.status}`);
      }
    
      console.log("Pickup mis à jour avec succès.");
    
      // Mettre à jour l'état du frontend après la mise à jour réussie
      onUpdatePickup(updatedPickup);
    } catch (error) {
      console.error("Erreur lors de la mise à jour de la livraison :", error);
      alert(`Erreur lors de la mise à jour de la livraison : ${error.message}`);
    }
  };

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

  // Fonction pour jouer le circuit progressivement
  const playCircuit = () => {
    if (!circuit || circuit.segments.length === 0) return;

    setIsPlaying(true);
    setPlayIndex(0);

    let index = 0;
    const interval = setInterval(() => {
      if (index >= circuit.segments.length - 1) {
        clearInterval(interval);
        setIsPlaying(false);
      } else {
        index++;
        setPlayIndex(index);
      }
    }, 0.1); // 0.1 ms par segment
  };

  // Segments pour le chemin joué en rose
  const playedPolylines = useMemo(() => {
    if (!circuit || circuit.segments.length === 0) return [];
    return circuit.segments.slice(0, playIndex + 1).map((segment) => {
      const origine = plan.points.find((p) => p.id === segment.origine);
      const destination = plan.points.find((p) => p.id === segment.destination);
      return origine && destination
        ? [
            [origine.latitude, origine.longitude],
            [destination.latitude, destination.longitude],
          ]
        : null;
    });
  }, [circuit, playIndex, plan]);

  // Marqueurs pour les livraisons
  {
    livraisons.map((livraison, index) => {
      const pickup = plan.points.find((p) => p.id === livraison.pickup);
      const delivery = plan.points.find((p) => p.id === livraison.destination);
      if (!pickup || !delivery) return null;

      const color = getStableColor(livraison.pickup);

      const stylePickupColor = `background-color: ${color};`;

      const iconPickup = divIcon({
        html: `<span class="map-marker-pickup" style="${stylePickupColor}"/>`,
      });
      const iconDelivery = divIcon({
        html: `<span class="map-marker-delivery" style="${stylePickupColor}"/>`,
      });

      return (
        <div key={index}>
          <Marker
            position={[pickup.latitude, pickup.longitude]}
            icon={iconPickup}
            draggable={true} // Rend le marqueur draggable
            eventHandlers={{
              dragend: (e) => handlePickupDragEnd(index, e.target.getLatLng()), // Appelle handleDragEnd avec la nouvelle position
            }}
          />
          <Marker
            position={[delivery.latitude, delivery.longitude]}
            icon={iconDelivery}
            draggable={true} // Rend le marqueur draggable
            eventHandlers={{
              dragend: (e) => handleDragEnd(index, e.target.getLatLng()), // Appelle handleDragEnd avec la nouvelle position
            }}
          />
        </div>
      );
    });
  }

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

        {/* Circuit complet */}
        {circuit?.segments.map((segment, index) => {
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

        {/* Chemin joué en rose */}
        {playedPolylines.map((polyline, index) => (
          <Polyline
            key={`play-${index}`}
            positions={polyline}
            color="hotpink"
            weight={8}
          />
        ))}

        {/* Marqueurs des livraisons */}
        {livraisons.map((livraison, index) => {
          const pickup = plan.points.find((p) => p.id === livraison.pickup);
          const delivery = plan.points.find(
            (p) => p.id === livraison.destination
          );
          if (!pickup || !delivery) return null;

          const color = getStableColor(livraison.pickup);

          const stylePickupColor = `background-color: ${color};`;

          const iconPickup = divIcon({
            html: `<span class="map-marker-pickup" style="${stylePickupColor}"/>`,
          });
          const iconDelivery = divIcon({
            html: `<span class="map-marker-delivery" style="${stylePickupColor}"/>`,
          });

          return (
            <div key={index}>
              <Marker
                position={[pickup.latitude, pickup.longitude]}
                icon={iconPickup}
                draggable={true} // Rend le marqueur draggable
                eventHandlers={{
                  dragend: (e) => handlePickupDragEnd(index, e.target.getLatLng()), // Appelle handleDragEnd avec la nouvelle position
                }}
              />
              <Marker
                position={[delivery.latitude, delivery.longitude]}
                icon={iconDelivery}
                draggable={true} // Rend le marqueur draggable
                eventHandlers={{
                  dragend: (e) => handleDragEnd(index, e.target.getLatLng()), // Appelle handleDragEnd avec la nouvelle position
                }}
              />
            </div>
          );
        })}

        {/* Marqueur de l'entrepôt */}
        {warehouseMarker && <Marker {...warehouseMarker} />}
      </MapContainer>

      {/* Bouton Play */}
      <div className="bg-white p-2 shadow-md flex justify-center">
        <button
          onClick={playCircuit}
          className="bg-pink-500 text-white px-4 py-1 rounded"
          disabled={isPlaying}
        >
          Play
        </button>
      </div>
    </div>
  );
}