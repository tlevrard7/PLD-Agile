import { Point } from "@/types/Plan";
const root = process.env.NEXT_PUBLIC_API_URL + '/map';

export default class MapServices {
    public static getRandomPoint(): Promise<Point> {
        return fetch(root+'/random-point').then(data => data.json());
    }
}