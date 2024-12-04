import React, { ComponentProps, useEffect, useRef } from 'react'

export interface CanvasProps extends ComponentProps<'canvas'> {
    draw: (ctx: CanvasRenderingContext2D, frameCount: number) => void
}

export default function Canvas({ draw, width, height, ...props }: CanvasProps) {

    const canvasRef = useRef<HTMLCanvasElement>(null)

    useEffect(() => {
        const canvas = canvasRef.current
        const context = canvas?.getContext('2d')
        let animationFrameId = 0;
        
        if (canvas != null && context != null) {
            canvas.width = 1000;
            canvas.height = 1000;
            let frameCount = 0
            const render = () => {
                draw(context, frameCount)
                animationFrameId = window.requestAnimationFrame(render)
                frameCount++
            }
            render()
        }

        return () => window.cancelAnimationFrame(animationFrameId)
    }, [draw])

    return <canvas ref={canvasRef} {...props} />
}