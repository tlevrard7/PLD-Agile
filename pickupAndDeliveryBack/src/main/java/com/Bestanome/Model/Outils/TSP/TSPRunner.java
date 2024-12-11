package com.Bestanome.Model.Outils.TSP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.Bestanome.Model.Objets.Livraisons.Tournee;
import com.Bestanome.Model.Objets.Plan.Plan;
import com.Bestanome.Model.Objets.Plan.Point;
import com.Bestanome.Model.Objets.Plan.Segment;
import com.Bestanome.Model.Data;

import org.apache.commons.lang3.tuple.Pair;

public class TSPRunner {
	private Map<Long, Point> mapPoint; // id Point -> objet Point
	private Map<Long, Map<Long, Segment>> mapSegment; // id Point origine + id Point destination -> objet Segment

	public TSPRunner(){
		mapPoint = new HashMap<>();
		mapSegment = new HashMap<>();
	}

	public void loadMap(Plan plan){
		// Remplir la Map des points
		for (Point point : plan.getPoints()) {
			mapPoint.put(point.getId(), point);
		}
		
		// Remplir la Map 
		for (Segment segment : plan.getSegments()) {
			Map<Long, Segment> innerMap = mapSegment.get(segment.getOrigine());
			if (innerMap == null) {
				// Créer une nouvelle Map si elle n'existait pas
				innerMap = new HashMap<>();
				mapSegment.put(segment.getOrigine(), innerMap);
			}

			// Insére le segment
			innerMap.put(segment.getDestination(), segment);
		}
	}

	private static Double getHarvensineLength(Point p1, Point p2){
		final int EARTH_RADIUS = 6378137; // Earth radius in meters
		
		// Convert degrees to radians
        Double lat1Rad = Math.toRadians(p1.getLatitude());
        Double lon1Rad = Math.toRadians(p1.getLongitude());
        Double lat2Rad = Math.toRadians(p2.getLatitude());
        Double lon2Rad = Math.toRadians(p2.getLongitude());

        // Calculate the differences
        Double deltaLat = lat2Rad - lat1Rad;
        Double deltaLon = lon2Rad - lon1Rad;

        // Haversine formula
        Double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                 + Math.cos(lat1Rad) * Math.cos(lat2Rad)
                 * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Calculate the distance
        return EARTH_RADIUS * c; // Distance in kilometers
	}

	private Double heristic(State state){
		Point point = mapPoint.get(state.point);
		Double min = Double.MAX_VALUE;
		for (Pair<Pair<Long, Boolean>, Pair<Long, Boolean>> currentLivraison : state.deliveryInfo){
			if (currentLivraison.getKey().getRight() && !currentLivraison.getValue().getRight()){
				Double dist = getHarvensineLength(point, mapPoint.get(currentLivraison.getValue().getLeft()));
				if (dist < min) min = dist;
			}
			else {
				Double dist = getHarvensineLength(point, mapPoint.get(currentLivraison.getKey().getLeft()));
				if (dist < min) min = dist;
			}
		}
		if (min == Double.MAX_VALUE){
			min = getHarvensineLength(point, mapPoint.get(Data.idEntrepot));
		}
		return min;
	}

	private State getMinState(ArrayList<State> opened, Map<Long, Double> g, Double w){
		State minState = null;
		Double minCost = Double.MAX_VALUE;
		for(State possibleState : opened){
			Double cost = g.get(possibleState.point) + w * heristic(possibleState);
			if(cost < minCost){
				minCost = cost;
				minState = possibleState;		
			}
		}
		return minState;
	}

	private ArrayList<Segment> action(State state){
		Map<Long, Segment> innerMap = mapSegment.get(state.point);
		ArrayList<Segment> adjacentSegments = new ArrayList<Segment>(innerMap.values());
		return adjacentSegments;
	}

	private State transition(State state, Segment action){		
		State newState = new State();
		newState.point = action.getDestination();
		newState.deliveryInfo = new Pair[state.deliveryInfo.length];
		
		for (int i = 0; i < state.deliveryInfo.length; i++) {
			Pair<Long, Boolean> firstPairCopy = Pair.of(state.deliveryInfo[i].getKey().getLeft(), state.deliveryInfo[i].getKey().getRight());
            Pair<Long, Boolean> secondPairCopy = Pair.of(state.deliveryInfo[i].getValue().getLeft(), state.deliveryInfo[i].getValue().getRight());

            newState.deliveryInfo[i] = Pair.of(firstPairCopy, secondPairCopy);
		}
		
		return newState; 
	}
	
	private Double cost(Segment action){return action.getLongueur();}

	private Boolean testFinal(State state){
		Boolean livraisonsFaites = true;
		for(Pair<Pair<Long, Boolean>, Pair<Long, Boolean>> liv : state.deliveryInfo){
			livraisonsFaites = livraisonsFaites && liv.getValue().getRight();
		}
		return (Data.idEntrepot == state.point) && livraisonsFaites;
	}

	private Map<Long, Double> initG(){
		Map<Long, Double> g = new HashMap<>();
		for(Long idP : mapPoint.keySet()){
			g.put(idP, Double.MAX_VALUE);
		}
		return g;
	}

	private Map<Long, Long> initPred(){
		Map<Long, Long> pred = new HashMap<>();
		return pred;
	}

	public Map<Long, Long> runTSP(State initialState, Double w){
		// Fonction AWA*(E, F, A, einit, actions, t, h, w){
			// Initialisations
			Double borne = Double.MAX_VALUE;
			Map<Long, Double> g = initG();
			g.put(initialState.point, 0d);
			Map<Long, Long> pred = initPred();
			
			ArrayList<State> closed = new ArrayList<State>();
			ArrayList<State> opened =  new ArrayList<State>();
			opened.add(initialState);
		
			// AWA*
			while(opened.size() > 0){
				State currentState = getMinState(opened, g, w);
				for(Segment a : action(currentState)){
					State possibleState = transition(currentState, a);
					Double duCourantCout = g.get(currentState.point) + cost(a);
					Double coutActuel = g.get(possibleState.point);
					if(duCourantCout < coutActuel){
						g.put(possibleState.point, duCourantCout);
						pred.put(possibleState.point, currentState.point);
						if(testFinal(possibleState)) {
							borne = g.get(possibleState.point);
							// Test if borne suffisante return pred;
							System.out.println(borne);
						}
						else if(g.get(possibleState.point) + heristic(possibleState) < borne) {
							opened.add(possibleState);
						}
						
					}
				}
				opened.remove(currentState);
				closed.add(currentState);
			}
			return pred;
			
			
			
			
		// }
			
	}



	/*
	public static void main(String[] args) {
		TSP tsp = new TSP3();
		for (int nbVertices = 8; nbVertices <= 16; nbVertices += 2){
			System.out.println("Graphs with "+nbVertices+" vertices:");
			Graph g = new CompleteGraph(nbVertices);
			long startTime = System.currentTimeMillis();
			tsp.searchSolution(20000, g);
			System.out.print("Solution of cost "+tsp.getSolutionCost()+" found in "
					+(System.currentTimeMillis() - startTime)+"ms : ");
			for (int i=0; i<nbVertices; i++)
				System.out.print(tsp.getSolution(i)+" ");
			System.out.println("0");
		}
	}
	*/

}
