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
    nomRue: string
    longueur: number
    origine: number
    destination: number
}

export type TypePoint = 'ENTREPOT' | 'INTERSECTION' | 'INTERSECTION' | 'DESTINATION' | 'PICKUP'
