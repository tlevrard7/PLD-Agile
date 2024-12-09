import { MapContainer, TileLayer, Polyline, Marker, ImageOverlay, MarkerProps } from "react-leaflet";
import "leaflet/dist/leaflet.css";
import { Plan } from "@/types/Plan";
import { useMemo } from "react";
import { Livraison } from "@/types/Livraison";
import { LatLng, divIcon} from "leaflet";

export interface InteractiveMapProps {
  plan: Plan;
  livraisons: Livraison[];
}

export default function InteractiveMap({ plan, livraisons }: InteractiveMapProps) {
  const polylines = useMemo(
    () =>
      plan.segments
        .map((segment) => {
          const origine = plan.points.find((p) => p.id === segment.origine);
          const destination = plan.points.find((p) => p.id === segment.destination);
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

  const livraisonMarkers = useMemo(() => {
    const markers: MarkerProps[] = []
    for (let i = 0; i < livraisons.length; i++) {
      const livraison = livraisons[i];
      const pickup = plan.points.find((p) => p.id === livraison.pickup);
      const delivery = plan.points.find((p) => p.id === livraison.destination);
      if (pickup == null || delivery == null) continue;
      const color = `hsl(${i / livraisons.length * 360}, 100%, 33%)`

      const styleDickupstyleColor = `background-color: ${color};`

      const iconPickup = divIcon({ html: `<span class="map-marker map-marker-pickup" style="${styleDickupstyleColor}"/>` })
      const iconDelivery = divIcon({ html: `<span class="map-marker" style="${styleDickupstyleColor}"/>` })
      markers.push({position: new LatLng(pickup.latitude, pickup.longitude), icon:iconPickup})
      markers.push({ position: new LatLng(delivery.latitude, delivery.longitude), icon: iconDelivery })
    }
    return markers
  }, [livraisons])

  return (
    <MapContainer
      center={[plan.points[0].latitude, plan.points[0].longitude]}
      zoom={13}
      style={{ width: "100%", height: "100%" }}
    >
      <TileLayer url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png" />

      {polylines.map((polyline, index) => <Polyline key={index} positions={polyline} color="blue" />)}
      {livraisonMarkers.map((props, index) => <Marker {...props} key={index}/>)}
    </MapContainer>
  );
}
