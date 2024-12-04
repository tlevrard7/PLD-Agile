import { Button, Upload } from "antd";
import { useContext, useState } from "react";
import MapService from "@/services/map-service";
import DeliveryMap from "@/components/DeliveryMap";
import { CurrentPlanContext } from "@/app/page";

export default function Test() {
    const [loading, setLoading] = useState(false);
    const { plan, setPlan } = useContext(CurrentPlanContext)

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
        </div>
    );
}