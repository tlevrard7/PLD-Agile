import { Point, Plan } from "@/types/Plan";
const root = process.env.NEXT_PUBLIC_API_URL + '/map';

export default class MapService {
    public static async uploadMap(file: File): Promise<Plan> {
        const formData = new FormData();
        formData.append("file", file);

        return fetch(`${root}/upload-xml`, {
            method: "POST",
            body: formData,
        }).then(response => response.json())
        .catch(error => {
            throw new Error(`Erreur lors de l'upload : ${error}`);

        });
    }
}