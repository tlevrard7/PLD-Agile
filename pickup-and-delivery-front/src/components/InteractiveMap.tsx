import { MapContainer, TileLayer, Polyline } from "react-leaflet";
import "leaflet/dist/leaflet.css";
import { Plan } from "@/types/Plan";
import { useMemo } from "react";

interface InteractiveMapProps {
  plan: Plan;
}

export default function InteractiveMap({ plan }: InteractiveMapProps) {
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

  return (
    <MapContainer
      center={[plan.points[0].latitude, plan.points[0].longitude]}
      zoom={13}
      style={{ width: "100%", height: "100%" }}
    >
      <TileLayer url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png" />

      {polylines.map((polyline, index) => (
        <Polyline key={index} positions={polyline} color="blue" />
      ))}
    </MapContainer>
  );
}
