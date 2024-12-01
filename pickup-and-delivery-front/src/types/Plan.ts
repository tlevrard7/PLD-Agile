export interface Point {
    id: number;
    longitude: number;
    latitude: number;
    type: TypePoint;
}

export enum TypePoint {
    ENTREPOT = 'ENTREPOT',
    INTERSECTION = 'INTERSECTION',
    PICKUP = 'PICKUP',
    DESTINATION = 'DESTINATION',
}