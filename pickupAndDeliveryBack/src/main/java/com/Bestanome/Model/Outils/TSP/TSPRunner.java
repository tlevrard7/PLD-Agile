package com.Bestanome.Model.Outils.TSP;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import com.Bestanome.Model.Data;
import com.Bestanome.Model.Objets.Livraisons.Circuit;
import com.Bestanome.Model.Objets.Livraisons.Livraison;
import com.Bestanome.Model.Objets.Livraisons.Tournee;
import com.Bestanome.Model.Objets.Plan.Plan;
import com.Bestanome.Model.Objets.Plan.Point;
import com.Bestanome.Model.Objets.Plan.Segment;


public class TSPRunner {
	private static Map<Long, Point> mapPoint; // id Point -> objet Point
	private static Map<Long, Map<Long, Segment>> mapSegment; // id Point origine -> (id Point destination -> Segment)
	private static Long temporaryObjective; // Objectif temporaire pour l'algorithme WA*
	private static boolean initiated = false;
	private static Double WA_weight = 1.0;

	public static boolean isInitiated() {
		return initiated;
	}

	/**
	 * Initialise le graphe avec les points et segments du plan donné.
	 *
	 * @param plan Le plan contenant les points et les segments.
	 */
	public static void initiate(Plan plan) {
		if (plan == null || plan.getPoints().isEmpty() || plan.getSegments().isEmpty()) {
			throw new IllegalStateException("Le plan est invalide ou vide");
		}

		mapPoint = new HashMap<>();
		mapSegment = new HashMap<>();

		for (Point point : plan.getPoints()) {
			mapPoint.put(point.getId(), point);
		}

		for (Segment segment : plan.getSegments()) {
			mapSegment.computeIfAbsent(segment.getOrigine(), k -> new HashMap<>())
					.put(segment.getDestination(), segment);
		}

		initiated = true;
		System.out.println("TSPRunner initialisé avec succès avec "
				+ mapPoint.size() + " points et " + mapSegment.size() + " segments.");
	}

	public static class FindCircuitState{
		public Long point;
		public Map<Long, ArrayList<Long>> pickUPtoDelivery;
		public Circuit circuit;
		public ArrayList<Long> opened;

		public FindCircuitState(ArrayList<Livraison> deliveries){
				this.point = Data.idEntrepot;
				this.opened = new ArrayList<>();
				this.pickUPtoDelivery = new HashMap<>();
				for(Livraison delviery : deliveries){
					opened.add(delviery.getPickup());
					pickUPtoDelivery.computeIfAbsent(delviery.getPickup(), key -> new ArrayList<>()).add(delviery.getDestination());
				}
				this.circuit = new Circuit();
		}

		public void addNewPossibilities(Long pickup){
			if(pickUPtoDelivery.containsKey(pickup)) opened.addAll(pickUPtoDelivery.get(pickup));
		}

		public void addSegmentsToStateCircuit(Long destPoint, WARunResult result){
			Long current = destPoint;
			ArrayList<Segment> segments = new ArrayList<>();
			while (!current.equals(this.point)) {
				Long predecessor = result.predecessors.get(current);
				if (predecessor == null) {
					throw new IllegalStateException("No path found between " + this.point + " and " + destPoint);
				}
				Segment segment = mapSegment.get(predecessor).get(current);
				segments.add(0, segment); // Ajouter le segment au début de la liste
				current = predecessor;
			}
			segments.forEach(this.circuit::ajouterSegment);
		}
	}

	public static Circuit findCircuit(Tournee tournee) {
		if (!initiated) {
			throw new IllegalStateException("TSP wasn't initiated with a valid graph");
		}
		FindCircuitState state = new FindCircuitState(tournee.getLivraisons());
		do{
			// Find greedy circuit (nearest neighbour)
			WARunResult greedyResult = new WARunResult();
			Double minCost = Double.MAX_VALUE;
			Long minPoint = null;
			for(Long possiblePoint : state.opened){
				if(getHaversineLength(state.point, possiblePoint) < minCost){
					WARunResult possibleResult = runWA(state.point, possiblePoint, WA_weight);
					if(possibleResult.found && possibleResult.costs.get(possiblePoint) < minCost){
						minCost = possibleResult.costs.get(possiblePoint);
						minPoint = possiblePoint;
						greedyResult = possibleResult;
					}
				}
			}
			
			if (greedyResult.found && minPoint != null) {
				// Add greedy circuit to resulting circuit from predecessors
				state.addSegmentsToStateCircuit(minPoint, greedyResult);

				// Add new possible delivery points if passed by a pickup
				state.addNewPossibilities(minPoint);

				// Prepare for finding next point
				state.point = minPoint;
				state.opened.remove(minPoint);

			} else {
				throw new IllegalStateException("No path found between " + state.point + " and " + minPoint);
			}
		}while(!state.opened.isEmpty());

		// Go back to pickup
		WARunResult backToWarehouseResult = runWA(state.point, Data.idEntrepot, WA_weight);
		state.addSegmentsToStateCircuit(Data.idEntrepot, backToWarehouseResult);
		
		return state.circuit;
	}

	/**
	 * Calcule la distance Haversine entre deux points.
	 *
	 * @param p1 Le premier point.
	 * @param p2 Le deuxième point.
	 * @return La distance en mètres.
	 */
	public static Double getHaversineLength(Point p1, Point p2) {
		final int EARTH_RADIUS = 6378137; // Rayon de la Terre en mètres

		double lat1Rad = Math.toRadians(p1.getLatitude());
		double lon1Rad = Math.toRadians(p1.getLongitude());
		double lat2Rad = Math.toRadians(p2.getLatitude());
		double lon2Rad = Math.toRadians(p2.getLongitude());

		double deltaLat = lat2Rad - lat1Rad;
		double deltaLon = lon2Rad - lon1Rad;

		double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
				+ Math.cos(lat1Rad) * Math.cos(lat2Rad)
						* Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return EARTH_RADIUS * c; // Distance en mètres
	}

	public static Double getHaversineLength(Long idP1, Long idP2) {
		Point p1 = mapPoint.get(idP1);
		Point p2 = mapPoint.get(idP2);
		return getHaversineLength(p1, p2);
	}

	private static boolean testFinal(Long state) {
		return state.equals(temporaryObjective);
	}

	private static Iterable<Segment> actions(Long state) {
		return mapSegment.getOrDefault(state, new HashMap<>()).values();
	}

	private static class WARunResult {
		public boolean found = false;
		public Map<Long, Long> predecessors = new HashMap<>();
		public Map<Long, Double> costs = new HashMap<>();
	}

	private static class WAStateComparator implements Comparator<Long> {
		@Override
		public int compare(Long p1, Long p2) {
			return Double.compare(getHaversineLength(p1, temporaryObjective),
					getHaversineLength(p2, temporaryObjective));
		}
	}

	public static WARunResult runWA(Long initialState, Long endState, Double w) {
		if (!initiated) {
			throw new IllegalStateException("TSP wasn't initiated with a valid graph");
		}

		temporaryObjective = endState;
		PriorityQueue<Long> opened = new PriorityQueue<>(new WAStateComparator());
		WARunResult result = new WARunResult();
		result.costs.put(initialState, 0.0);
		opened.add(initialState);

		while (!opened.isEmpty()) {
			Long currentState = opened.poll();

			if (testFinal(currentState)) {
				result.found = true;
				return result;
			}

			for (Segment a : actions(currentState)) {
				Long possibleState = a.getDestination();
				Double costFromCurrent = result.costs.get(currentState)
						+ getHaversineLength(a.getOrigine(), a.getDestination());
				Double possibleCost = result.costs.get(possibleState);

				if (possibleCost == null || costFromCurrent < possibleCost) {
					result.costs.put(possibleState, costFromCurrent);
					result.predecessors.put(possibleState, currentState);

					if (!opened.contains(possibleState)) {
						opened.add(possibleState);
					}
				}
			}
		}

		return result;
	}
}