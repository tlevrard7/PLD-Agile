export interface Plan {
    points: Point[]
    segments: Segment[]
}

export interface Point {
    id: number;
    longitude: number;
    latitude: number;
    type: TypePoint;
}

export interface Segment {
    id: number;
    pointA: number
    pointB: number
}

export enum TypePoint {
    ENTREPOT = 'ENTREPOT',
    INTERSECTION = 'INTERSECTION',
    PICKUP = 'PICKUP',
    DESTINATION = 'DESTINATION',
}