import { Point } from "@/types/Plan";
const root = process.env.NEXT_PUBLIC_API_URL + '/map';

export default class MapService {
    public static async uploadMap(file: File): Promise<Plan> {
        const formData = new FormData();
        formData.append("file", file);

        const response = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/map/upload-xml`, {
            method: "POST",
            body: formData,
        });

        console.log("Response status:", response.status); // Ajoutez ce log
        const json = await response.json();
        console.log("Response JSON:", json); // Ajoutez ce log

        if (!response.ok) {
            throw new Error("Failed to upload map.");
        }
        return json;
    }
}