import { Plan } from "@/types/Plan";
import Canvas, { CanvasProps } from "./Canvas";
import { useMemo, useState } from "react";

export interface MapsProps extends Omit<CanvasProps, "draw"> {
  plan: Plan;
  zoom: number;
}

export default function DeliveryMap({ plan, zoom, ...canvasProps }: MapsProps) {
  const [offset, setOffset] = useState({ x: 0, y: 0 });
  const [isPanning, setIsPanning] = useState(false);
  const [startPan, setStartPan] = useState({ x: 0, y: 0 });

  const pointsCoords = useMemo(() => {
    const margin = 0.05;
    const allLats = plan.points.map((p) => p.latitude);
    const allLongs = plan.points.map((p) => p.longitude);
    const minLat = Math.min(...allLats);
    const maxLat = Math.max(...allLats);
    const minLong = Math.min(...allLongs);
    const maxLong = Math.max(...allLongs);

    const normalize = (value: number, min: number, max: number) =>
      (value - min) / (max - min);

    const coords: { [id: number]: { x: number; y: number } } = {};
    for (const p of plan.points) {
      coords[p.id] = {
        x: normalize(p.longitude, minLong, maxLong),
        y: 1 - normalize(p.latitude, minLat, maxLat),
      };
    }
    return coords;
  }, [plan]);

  function drawMap(ctx: CanvasRenderingContext2D, frameCount: number) {
    ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);
    ctx.save();
    ctx.translate(offset.x, offset.y);
    ctx.scale(zoom, zoom);

    // Dessiner les segments
    ctx.strokeStyle = "#000";
    ctx.lineWidth = 1 / zoom;
    for (const s of plan.segments) {
      const start = pointsCoords[s.origine];
      const end = pointsCoords[s.destination];
      if (start && end) {
        ctx.beginPath();
        ctx.moveTo(start.x * ctx.canvas.width, start.y * ctx.canvas.height);
        ctx.lineTo(end.x * ctx.canvas.width, end.y * ctx.canvas.height);
        ctx.stroke();
      }
    }

    // Dessiner les points
    ctx.fillStyle = "#ff0000";
    for (const p of plan.points) {
      const point = pointsCoords[p.id];
      if (point) {
        ctx.beginPath();
        ctx.arc(
          point.x * ctx.canvas.width,
          point.y * ctx.canvas.height,
          3 / zoom,
          0,
          2 * Math.PI
        );
        ctx.fill();
      }
    }

    ctx.restore();
  }

  // Gestion des événements de la souris pour le panning
  const handleMouseDown = (e: React.MouseEvent) => {
    if (e.button === 2) { // Clic droit
      setIsPanning(true);
      setStartPan({ x: e.clientX - offset.x, y: e.clientY - offset.y });
    }
  };

  const handleMouseMove = (e: React.MouseEvent) => {
    if (isPanning) {
      setOffset({ x: e.clientX - startPan.x, y: e.clientY - startPan.y });
    }
  };

  const handleMouseUp = () => {
    setIsPanning(false);
  };

  return (
    <div
      onMouseDown={handleMouseDown}
      onMouseMove={handleMouseMove}
      onMouseUp={handleMouseUp}
      onContextMenu={(e) => e.preventDefault()} // Empêcher le menu contextuel
      className="w-full h-full"
    >
      <Canvas draw={drawMap} {...canvasProps} />
    </div>
  );
}