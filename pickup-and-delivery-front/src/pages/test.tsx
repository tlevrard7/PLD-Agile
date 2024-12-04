import { Button, Upload } from "antd";
import { useContext, useState } from "react";
import MapService from "@/services/map-service";
import DeliveryMap from "@/components/DeliveryMap";
import { CurrentPlanContext } from "@/app/page";

export default function Test() {
    const [loading, setLoading] = useState(false);
    const { plan, setPlan } = useContext(CurrentPlanContext)

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
        </div>
    );
}