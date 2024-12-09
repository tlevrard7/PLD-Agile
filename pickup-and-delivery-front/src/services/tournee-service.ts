import { Livraison } from "@/types/Livraison";
const root = process.env.NEXT_PUBLIC_API_URL + '/delivery';

export default class TourneeService {
    public static async uploadLivraisons(file: File): Promise<Livraison[]> {
        const formData = new FormData();
        formData.append("file", file);

        const response = await fetch(`${root}/map/upload-livraisons`, {
            method: "POST",
            body: formData,
        });

        console.log("Response status:", response.status); // Ajoutez ce log
        const json = await response.json();
        console.log("Response JSON:", json); // Ajoutez ce log

        if (!response.ok) {
            throw new Error(`Erreur lors de l'upload : ${response.statusText}`);
        }
        return json;
    }
}


