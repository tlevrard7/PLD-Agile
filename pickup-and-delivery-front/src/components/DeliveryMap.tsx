import { Plan } from "@/model/Plan";
import Canvas, { CanvasProps } from "./Canvas";
import { useMemo } from "react";

export interface MapsProps extends Omit<CanvasProps, 'draw'> {
    plan: Plan
}

export default function DeliveryMap({ plan, ...canvasProps }: MapsProps) {
    const pointsCoords: {[id: number]: { x: number, y: number }} = useMemo(() => {
        const margin = 0
        const allLats = plan.points.map(p => p.latitude).sort();
        const allLongs = plan.points.map(p => p.longitude).sort();
        const normalize = (value: number, min: number, max: number) => (value - (min - margin)) / (max - min + 2 * margin)
        
        console.log(plan.points.map(p => ({
            x: normalize(p.latitude, allLats[0], allLats[allLats.length - 1]),
            y: normalize(p.longitude, allLongs[0], allLongs[allLongs.length - 1]),
        })))
        const coords: { [id: number]: { x: number, y: number } } = {}
        for (const p of plan.points) {
            coords[p.id] = {
                x: normalize(p.latitude, allLats[0], allLats[allLats.length - 1]),
                y: normalize(p.longitude, allLongs[0], allLongs[allLongs.length - 1]),
            }
        }
        return coords
    }, [plan])
    
    function drawMap(ctx: CanvasRenderingContext2D, frameCount: number) {
        if (frameCount != 0) return
        ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height)
        ctx.fillStyle = '#ff0000'
        
        for (const s of plan.segments) {
            ctx.beginPath()
            ctx.moveTo(pointsCoords[s.origine].x * ctx.canvas.width, pointsCoords[s.origine].y * ctx.canvas.height)
            ctx.lineTo(pointsCoords[s.destination].x * ctx.canvas.width, pointsCoords[s.destination].y * ctx.canvas.height)
            ctx.stroke()
        }
        console.log(pointsCoords)
        for (const p of plan.points) {
            ctx.beginPath()
            ctx.arc(pointsCoords[p.id].x * ctx.canvas.width, pointsCoords[p.id].y * ctx.canvas.height, 1, 0, 2 * Math.PI)
            ctx.fill()
        }
    }
    return <Canvas draw={drawMap} {...canvasProps} />
}