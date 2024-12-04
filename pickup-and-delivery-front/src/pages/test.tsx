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
        if (!file) {
          console.error("No file selected");
          return;
        }
      
        setLoading(true);
      
        try {
          const formData = new FormData();
          formData.append("file", file);
      
          const response = await fetch(
            `${process.env.NEXT_PUBLIC_API_URL}/map/upload-xml`,
            {
              method: "POST",
              body: formData,
            }
          );
      
          if (!response.ok) {
            throw new Error(`Failed to upload map: ${response.statusText}`);
          }
      
          const planData = await response.json();
          setPlan(planData);
        } catch (error) {
          console.error("Failed to upload map:", error);
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