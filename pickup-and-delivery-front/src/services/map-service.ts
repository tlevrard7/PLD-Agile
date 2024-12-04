import { Point } from "@/types/Plan";
const root = process.env.NEXT_PUBLIC_API_URL + '/map';

export default class MapService {
    public static getRandomPoint(): Promise<Point> {
        return fetch(root+'/random-point').then(data => data.json());
    }

    public static uploadMap(file: File): Promise<Plan> {
        const formData = new FormData();
        formData.append("file", file);

        return fetch(root + '/upload-xml', {
            method: 'POST',
            body: formData,
        }).then((res) => {
            if (!res.ok) throw new Error('Failed to upload map.');
            return res.json();
        });
    }
}