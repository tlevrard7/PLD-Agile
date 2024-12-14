package com.Bestanome.Model.Outils.TSP;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import org.apache.commons.lang3.tuple.Pair;

import com.Bestanome.Model.Data;
import com.Bestanome.Model.Objets.Livraisons.Circuit;
import com.Bestanome.Model.Objets.Livraisons.Tournee;
import com.Bestanome.Model.Objets.Plan.Plan;
import com.Bestanome.Model.Objets.Plan.Point;
import com.Bestanome.Model.Objets.Plan.Segment;


public class TSPRunner {
	private static Map<Long, Point> mapPoint; // id Point -> objet Point
	private static Map<Long, Map<Long, Segment>> mapSegment; // id Point origine + id Point destination -> objet Segment
	private static Long temporaryObjective; // id Point origine + id Point destination -> objet Segment
	private static Boolean initiated = false;
	private static class StateComparator implements Comparator<Long>{
		@Override
    public int compare(Long p1, Long p2){
			return Double.compare(getHarvensineLength(p1,temporaryObjective), getHarvensineLength(p2,temporaryObjective));
		}
	}
	private static class RunResult{
		public Boolean found = false;
		public Map<Long,Long> predecessors;
		public Map<Long, Double> costs;

		public RunResult(){
			costs = new HashMap<>();
			// for(Long idP : mapPoint.keySet()){
			// 	costs.put(idP, Double.MAX_VALUE);
			// }
			predecessors = new HashMap<>();
		}
	}

	public static void initiate(Plan plan){
		mapPoint = new HashMap<>();
		mapSegment = new HashMap<>();

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
		initiated = true;
	}

	public static Double getHarvensineLength(Point p1, Point p2){
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
        return EARTH_RADIUS * c; // Distance in meters
	}

	public static Double getHarvensineLength(Long idP1, Long idP2){
		Point p1 = mapPoint.get(idP1);
		Point p2 = mapPoint.get(idP2);
		return getHarvensineLength(p1, p2);
	}

	// final Long getMinState(ArrayList<Long> opened, Map<Long, Double> costs, Double w){
	// 	Long minState = null;
	// 	Double minCost = Double.MAX_VALUE;
	// 	for(Long possibleState : opened){
	// 		Double cost = costs.get(possibleState) + w * heristic(possibleState);
	// 		if(cost < minCost){
	// 			minCost = cost;
	// 			minState = possibleState;		
	// 		}
	// 	}
	// 	return minState;
	// }

	private static Boolean testFinal(Long state){
		return state == temporaryObjective;
	}

	private static RunResult runWA(Long intialState, Long endState, Double w){ // Weighted A*
		assert !initiated : "TSP wasn't initated with a valid graph";
		temporaryObjective = endState;
		PriorityQueue<Long> opened =  new PriorityQueue<>(new StateComparator());
		RunResult result = new RunResult();
		opened.add(intialState);
		while(opened.size() > 0){
			// System.out.println("Open Before: {");
			// for(State s : opened){
			// 	System.out.println(s.toString());
			// }
			// System.out.println("}");
			//State currentState = getMinState(opened, costs, w);
			Long currentState = opened.poll();
			// System.out.println("Chosen: " + currentState.cost.toString());
			//System.out.println("passed: " + currentState.circuit.toString());	
			if(testFinal(currentState)){
				result.found = true;
				return result;
			} 
			for(Segment a : actions(currentState)){
				Long possibleState = a.getDestination(); // transition
				Double costFromCurrent = result.costs.get(currentState) + getHarvensineLength(a.getOrigine(), a.getDestination());
				Double possibleCost = result.costs.get(possibleState);
				if(possibleCost == null || costFromCurrent < possibleCost) {
					result.costs.put(possibleState, costFromCurrent);
					result.predecessors.put(possibleState, currentState);
					if(TEST IF NOT OPEN) opened.add(possibleState);
				}
			}
			opened.remove(currentState);
		}
		return null;
			
	}

	Function run WA for all pickups

	// public static void findCircuit(Tournee tournee){
	// 	State initialState = new State(Data.idEntrepot, tournee.getLivraisons());
	// 	Circuit circuit = runTSP(initialState, 1d);
		
	// }

	// private static Circuit TSPResultToCircuit(State finalstate){
	// 	Circuit circuit = new Circuit();
	// 	System.out.println(finalstate);
	// 	if(finalstate != null){
	// 		System.out.println("res " + (60.0 * (finalstate.cost + heristic(finalstate)) / 25000.0));
	// 		for(int i = 0; i < finalstate.circuit.size() - 1; i++){
	// 			Segment s = mapSegment.get(finalstate.circuit.get(i)).get(finalstate.circuit.get(i+1));
	// 			circuit.ajouterSegment(s);
	// 			System.out.println(s.getNomRue());
	// 		}
	// 	}
	// 	else{System.out.println("no possible");}
	// 	return circuit;
	// }

	// private static Double getHarvensineLength(Point p1, Point p2){
	// 	final int EARTH_RADIUS = 6378137; // Earth radius in meters
		
	// 	// Convert degrees to radians
  //       Double lat1Rad = Math.toRadians(p1.getLatitude());
  //       Double lon1Rad = Math.toRadians(p1.getLongitude());
  //       Double lat2Rad = Math.toRadians(p2.getLatitude());
  //       Double lon2Rad = Math.toRadians(p2.getLongitude());

  //       // Calculate the differences
  //       Double deltaLat = lat2Rad - lat1Rad;
  //       Double deltaLon = lon2Rad - lon1Rad;

  //       // Haversine formula
  //       Double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
  //                + Math.cos(lat1Rad) * Math.cos(lat2Rad)
  //                * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
  //       Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

  //       // Calculate the distance
  //       return EARTH_RADIUS * c; // Distance in meters
	// }

	// private static Double heristic(State state){
	// 	// Point point = mapPoint.get(state.point);
	// 	// Double min = Double.MAX_VALUE;
	// 	// for (Pair<Pair<Long, Boolean>, Pair<Long, Boolean>> currentLivraison : state.deliveryInfo){
	// 	// 	if (currentLivraison.getKey().getRight() && !currentLivraison.getValue().getRight()){
	// 	// 		Double dist = getHarvensineLength(point, mapPoint.get(currentLivraison.getValue().getLeft()));
	// 	// 		if (dist < min) min = dist;
	// 	// 	}
	// 	// 	else {
	// 	// 		Double dist = getHarvensineLength(point, mapPoint.get(currentLivraison.getKey().getLeft()));
	// 	// 		if (dist < min) min = dist;
	// 	// 	}
	// 	// }
	// 	// if (min == Double.MAX_VALUE){
	// 	// 	min = getHarvensineLength(point, mapPoint.get(Data.idEntrepot));
	// 	// }
	// 	// return min;
	// 	Point point = mapPoint.get(state.point);
	// 	Double total = 0d;
	// 	for (Pair<Pair<Long, Boolean>, Pair<Long, Boolean>> currentLivraison : state.deliveryInfo){
	// 		if (currentLivraison.getLeft().getRight() && !currentLivraison.getRight().getRight()){
	// 			total += getHarvensineLength(point, mapPoint.get(currentLivraison.getRight().getLeft()));
	// 		}
	// 		else {
	// 			total += getHarvensineLength(point, mapPoint.get(currentLivraison.getLeft().getLeft()));
	// 		}
	// 	}
	// 	if (total == 0){
	// 		total = getHarvensineLength(point, mapPoint.get(Data.idEntrepot));
	// 	}
	// 	return total;
	// }

	// private static State getMinState(ArrayList<State> opened, Double w){
	// 	State minState = null;
	// 	Double minCost = Double.MAX_VALUE;
	// 	for(State possibleState : opened){
	// 		Double cost = possibleState.cost + w * heristic(possibleState);
	// 		if(cost < minCost){
	// 			minCost = cost;
	// 			minState = possibleState;		
	// 		}
	// 	}
	// 	return minState;
	// }

	// private static ArrayList<Segment> action(State state){
	// 	Map<Long, Segment> innerMap = mapSegment.get(state.point);
	// 	ArrayList<Segment> adjacentSegments;
	// 	if(innerMap != null)adjacentSegments  = new ArrayList<Segment>(innerMap.values());
	// 	else adjacentSegments = new ArrayList<Segment>();
	// 	return adjacentSegments;
	// }

	// private static State transition(State state, Segment action){		
	// 	State newState = new State();
	// 	newState.point = action.getDestination();
	// 	newState.deliveryInfo = new Pair[state.deliveryInfo.length];
	// 	newState.passed = new HashMap<>(state.passed);
	// 	newState.passed.put(state.point, true);
	// 	newState.circuit = new ArrayList<Long>(state.circuit);
	// 	newState.circuit.add(action.getDestination());
		
	// 	for (int i = 0; i < state.deliveryInfo.length; i++) {
	// 		Pair<Long, Boolean> pickupInfo = state.deliveryInfo[i].getLeft();
	// 		Pair<Long, Boolean> destinationInfo =  state.deliveryInfo[i].getRight();
	// 		if(!(pickupInfo.getRight())){ // On n'a pas passé par le pickup
	// 			if(pickupInfo.getLeft() == newState.point) {
	// 				pickupInfo = Pair.of(pickupInfo.getLeft(),true); // il s'agit ici bien du pickup
	// 				newState.passed.clear();
	// 			}
	// 		}
	// 		else if(!(destinationInfo.getRight()) && (destinationInfo.getLeft() == newState.point)){ // On a passé par le pickup, mais pas le delivery et on y est ici
	// 			destinationInfo = Pair.of(destinationInfo.getLeft(),true);
	// 			newState.passed.clear();
	// 		}
	// 		newState.deliveryInfo[i] = Pair.of(pickupInfo, destinationInfo);
	// 	}
		
	// 	return newState; 
	// }
	
	// private static Double cost(Segment action){
	// 	return getHarvensineLength(mapPoint.get(action.getOrigine()), mapPoint.get(action.getDestination()));
	// 	//return action.getLongueur();
	// }

	// private static Boolean testFinal(State state){
	// 	Boolean livraisonsFaites = true;
	// 	for(Pair<Pair<Long, Boolean>, Pair<Long, Boolean>> liv : state.deliveryInfo){
	// 		livraisonsFaites = livraisonsFaites && liv.getValue().getRight();
	// 	}
	// 	return state.point == Data.idEntrepot && livraisonsFaites;
	// }

	// private static Map<Long, Double> initG(){
	// 	Map<Long, Double> g = new HashMap<>();
	// 	for(Long idP : mapPoint.keySet()){
	// 		g.put(idP, Double.MAX_VALUE);
	// 	}
	// 	return g;
	// }

	// public static Circuit runTSP(State initialState, Double w){
	// 	Double borne = 240d * 25000d;
	// 	PriorityQueue<State> opened =  new PriorityQueue<>(new StateComparator());
	// 	//ArrayList<State> opened = new ArrayList<>();
	// 	opened.add(initialState);
	
	// 	// AWA*
	// 	while(opened.size() > 0){
	// 		// System.out.println("Open Before: {");
	// 		// for(State s : opened){
	// 		// 	System.out.println(s.toString());
	// 		// }
	// 		// System.out.println("}");
	// 		//State currentState = getMinState(opened, w);
	// 		State currentState = opened.poll();
	// 		System.out.println("Chosen: " + currentState.cost.toString());
	// 		//System.out.println("passed: " + currentState.circuit.toString());
			
	// 		for(Segment a : action(currentState)){
	// 			if(currentState.passed.get(a.getDestination()) == null){
	// 				State possibleState = transition(currentState, a);
	// 				possibleState.cost = currentState.cost + cost(a);
			
	// 				if(testFinal(possibleState)) {
	// 					return TSPResultToCircuit(possibleState);
	// 					//if((60.0 * borne / 25000.0) < 5) return pred; // if time taken udner 5min return
	// 				}
	// 				else if(possibleState.cost < borne) {
	// 					opened.add(possibleState);
	// 				}
					
	// 			}
	// 		}
	// 		opened.remove(currentState);
	// 		// System.out.println("Open After: {");
	// 		// for(State s : opened){
	// 		// 	System.out.println(s.toString());
	// 		// }
	// 		// System.out.println("}\n\r");
	// 	}
	// 	return null;
			
	// }




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
