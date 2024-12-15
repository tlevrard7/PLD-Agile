import { LivraisonsEntrepot } from "@/types/Livraison";
const root = process.env.NEXT_PUBLIC_API_URL + '/delivery';

export default class TourneeService {
    public static async uploadLivraisons(file: File): Promise<LivraisonsEntrepot> {
        const formData = new FormData();
        formData.append("file", file);

        return fetch(`${root}/upload-deliveries`, {
            method: "POST",
            body: formData,
        }).then(response => response.json())
        .catch(error => {
            throw new Error(`Erreur lors de l'upload : ${error}`);
        });
    }
}


