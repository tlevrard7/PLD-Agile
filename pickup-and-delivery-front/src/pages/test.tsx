import { Button, Upload } from "antd";
import { LoadingOutlined } from '@ant-design/icons';
import { useState } from "react";
import MapService from "@/services/map-service";
import { Plan, Point, TypePoint } from "@/types/Plan";
import DeliveryMap from "@/components/DeliveryMap";

export default function Test() {
    const [plan, setPlan] = useState<Plan|null>(null);
    const [loading, setLoading] = useState(false);

    async function onUpload(file: File) {
        setLoading(true);
        try {
            const uploadedPlan = await MapService.uploadMap(file);
            setPlan(uploadedPlan);
        } catch (error) {
            console.error("Failed to upload map:", error);
            alert("Error uploading map. Check the console for details.");
        } finally {
            setLoading(false);
        }
    }

    return (
        <div>
            <h1>Map Upload and Display</h1>
            <Upload 
                beforeUpload={(file) => {
                    onUpload(file);
                    return false; // Prevent default upload
                }}
                showUploadList={false}
            >
                <Button loading={loading}>Upload Map</Button>
            </Upload>
            {plan && (
                <div>
                    <h2>Map Visualization</h2>
                    <DeliveryMap style={{ backgroundColor: 'gray', width: '50vw', height: '50vh' }} plan={plan} />
                </div>
            )}
        </div>
    );
}