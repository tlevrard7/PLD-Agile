import { MapContainer, TileLayer, Marker, Polyline, Popup } from "react-leaflet";
import "leaflet/dist/leaflet.css";
import { Plan } from "@/types/Plan";
import L from "leaflet";
import { useMemo } from "react";

interface InteractiveMapProps {
  plan: Plan;
}

const customIcon = new L.Icon({
  iconUrl: "/marker-icon.png",
  iconSize: [25, 41],
  iconAnchor: [12, 41],
});

export default function InteractiveMap({ plan }: InteractiveMapProps) {
  const markers = useMemo(
    () =>
      plan.points.map((point) => ({
        id: point.id,
        position: [point.latitude, point.longitude] as [number, number],
      })),
    [plan]
  );

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

      {markers.map((marker) => (
        <Marker key={marker.id} position={marker.position} icon={customIcon}>
          <Popup>
            <div>
              <p><strong>ID :</strong> {marker.id}</p>
              <p><strong>Latitude :</strong> {marker.position[0]}</p>
              <p><strong>Longitude :</strong> {marker.position[1]}</p>
            </div>
          </Popup>
        </Marker>
      ))}

      {polylines.map((polyline, index) => (
        <Polyline key={index} positions={polyline} color="blue" />
      ))}
    </MapContainer>
  );
}
