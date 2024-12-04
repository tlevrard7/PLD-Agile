import Canvas, { CanvasProps } from "@/components/Canvas";
import { useContext, useMemo } from "react";
import { CurrentPlanContext } from "@/app/page";

export interface MapsProps extends Omit<CanvasProps, 'draw'> { }

export default function PlanPanel({ ...canvasProps }: MapsProps) {
    const { plan } = useContext(CurrentPlanContext);

    const pointsCoords: { [id: number]: { x: number, y: number } } = useMemo(() => {
        if (plan == null) return []

        const margin = 0
        const allLats = plan.points.map(p => p.latitude).sort();
        const allLongs = plan.points.map(p => p.longitude).sort();
        const normalize = (value: number, min: number, max: number) => (value - (min - margin)) / (max - min + 2 * margin)
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
        if (plan == null) return
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
    return <div>
        <h2>Map Visualization</h2>
        {
            plan == null ?
            <p>Load a plan 1st</p>:
            <Canvas draw={drawMap} {...canvasProps} />
        }
    </div>
}