import { Button } from "antd";
import { LoadingOutlined } from '@ant-design/icons';
import { useState } from "react";
import MapService from "@/services/map-services";
import { Point } from "@/types/Plan";

export default function Test() {
    
    const [point, setPoint] = useState<Point|null>(null);
    const [loading, setLoading] = useState(false);

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
    </>
}