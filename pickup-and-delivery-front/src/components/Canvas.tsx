import React, { ComponentProps, useEffect, useRef } from 'react';

export interface CanvasProps extends ComponentProps<'canvas'> {
    draw: (ctx: CanvasRenderingContext2D, frameCount: number) => void;
}

export default function Canvas({ draw, ...props }: CanvasProps) {
    const canvasRef = useRef<HTMLCanvasElement>(null);

    useEffect(() => {
        const canvas = canvasRef.current;
        const context = canvas?.getContext('2d');
        let animationFrameId = 0;

        const resizeCanvas = () => {
            if (canvas) {
                canvas.width = canvas.parentElement?.clientWidth || 0;
                canvas.height = canvas.parentElement?.clientHeight || 0;
                if (context) draw(context, 0);
            }
        };

        resizeCanvas();
        window.addEventListener('resize', resizeCanvas);

        const render = () => {
            if (context) draw(context, 0);
            animationFrameId = window.requestAnimationFrame(render);
        };

        render();

        return () => {
            window.cancelAnimationFrame(animationFrameId);
            window.removeEventListener('resize', resizeCanvas);
        };
    }, [draw]);

    return <canvas ref={canvasRef} className="w-full h-full" {...props} />;
}