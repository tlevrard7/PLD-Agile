import { Button } from "antd";
import { LoadingOutlined } from '@ant-design/icons';
import { useState } from "react";
import MapService from "@/services/map-service";
import { Plan, Point, TypePoint } from "@/types/Plan";
import DeliveryMap from "@/components/DeliveryMap";

export default function Test() {
    
    const [point, setPoint] = useState<Point|null>(null);
    const [loading, setLoading] = useState(false);

    const plan: Plan = {
        points: [
            { id: 0, latitude: 46.0, longitude: 46.0, type: TypePoint.ENTREPOT },
            { id: 3, latitude: 47.0, longitude: 46.0, type: TypePoint.ENTREPOT },
            { id: 2, latitude: 47.0, longitude: 47.0, type: TypePoint.ENTREPOT },
            { id: 1, latitude: 46.0, longitude: 47.0, type: TypePoint.ENTREPOT },
        ],
        segments: [
            {id: 0, pointA: 0, pointB: 2},
        ]
    }

    async function onClickGet() {
        setLoading(true);
        await MapService.getRandomPoint()
            .then(p => setPoint(p))
            .finally(() => setLoading(false));
    }

    return <>
        <p>
            <Button onClick={onClickGet} loading={loading}>Fetch a random point</Button>
        </p>
        <p>
            Random point: {loading ? <><LoadingOutlined/> Fetching point...</> : JSON.stringify(point)}
        </p>
        <DeliveryMap style={{ backgroundColor: 'gray', width: '50vw', height: '50vh'}} plan={plan}/>
    </>
}