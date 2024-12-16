package com.Bestanome.Model.Outils.TSP;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import com.Bestanome.Model.Objets.Livraisons.Circuit;
import com.Bestanome.Model.Objets.Livraisons.Tournee;
import com.Bestanome.Model.Objets.Plan.Plan;
import com.Bestanome.Model.Objets.Plan.Point;
import com.Bestanome.Model.Objets.Plan.Segment;

public class TSPRunner {
	private static Map<Long, Point> mapPoint; // id Point -> objet Point
	private static Map<Long, Map<Long, Segment>> mapSegment; // id Point origine -> (id Point destination -> Segment)
	private static Long temporaryObjective; // Objectif temporaire pour l'algorithme
	private static boolean initiated = false;

	public static boolean isInitiated() {
		return initiated;
	}

	private static class StateComparator implements Comparator<Long> {
		@Override
		public int compare(Long p1, Long p2) {
			return Double.compare(getHaversineLength(p1, temporaryObjective),
					getHaversineLength(p2, temporaryObjective));
		}
	}

	private static class RunResult {
		public boolean found = false;
		public Map<Long, Long> predecessors = new HashMap<>();
		public Map<Long, Double> costs = new HashMap<>();
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

	/**
	 * Trouve et retourne le circuit optimal pour une tournée donnée.
	 *
	 * @param tournee La tournée à optimiser.
	 * @return Le circuit optimal.
	 */
	public static Circuit findCircuit(Tournee tournee) {
		if (!initiated) {
			throw new IllegalStateException("TSP wasn't initiated with a valid graph");
		}

		ArrayList<Long> points = PlanificateurLivraisons.ordonnancer(tournee.getLivraisons(),
				PlanificateurLivraisons::NNLivraisons);

		// Construire le circuit en utilisant runWA pour chaque paire de points
		Circuit circuit = new Circuit();
		for (int i = 0; i < points.size() - 1; i++) {
			Long start = points.get(i);
			Long end = points.get(i + 1);
			RunResult result = runWA(start, end, 1.0); // Utilise le poids w = 1.0

			if (result.found) {
				Long current = end;
				ArrayList<Segment> segments = new ArrayList<>();

				// Reconstruire le chemin en utilisant les prédécesseurs
				while (!current.equals(start)) {
					Long predecessor = result.predecessors.get(current);
					if (predecessor == null) {
						throw new IllegalStateException("No path found between " + start + " and " + end);
					}
					Segment segment = mapSegment.get(predecessor).get(current);
					segments.add(0, segment); // Ajouter le segment au début de la liste
					current = predecessor;
				}

				// Ajouter les segments au circuit
				segments.forEach(circuit::ajouterSegment);
			} else {
				throw new IllegalStateException("No path found between " + start + " and " + end);
			}
		}

		return circuit;
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

	public static RunResult runWA(Long initialState, Long endState, Double w) {
		if (!initiated) {
			throw new IllegalStateException("TSP wasn't initiated with a valid graph");
		}

		temporaryObjective = endState;
		PriorityQueue<Long> opened = new PriorityQueue<>(new StateComparator());
		RunResult result = new RunResult();
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