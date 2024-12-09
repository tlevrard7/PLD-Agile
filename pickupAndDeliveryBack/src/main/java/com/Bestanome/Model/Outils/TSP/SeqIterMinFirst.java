package com.Bestanome.Model.Outils.TSP;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class SeqIterMinFirst implements Iterator<Integer> {
	private Integer[] candidates;
	private int nbCandidates;

	/**
	 * Create an iterator to traverse the set of vertices in <code>unvisited</code> 
	 * which are successors of <code>currentVertex</code> in <code>g</code>
	 * Vertices are traversed in the same order as in <code>unvisited</code>
	 * @param unvisited
	 * @param currentVertex
	 * @param g
	 */
	public SeqIterMinFirst(Collection<Integer> unvisited, int currentVertex, Graph g){
		// Convert unvisited to a stream, filter for connected nodes, and collect as a list
		List<Integer> unvisitedList = unvisited.stream()
			.filter(node -> g.isArc(currentVertex, node)) // Check if there's an arc from currentVertex to node
			.sorted((node1, node2) -> {
				int cost1 = g.getCost(currentVertex, node1);
				int cost2 = g.getCost(currentVertex, node2);
				return Integer.compare(cost2, cost1); // Sort in descending order of cost
			})
			.toList();

    	// Convert the sorted list back to an Integer array
		this.candidates = unvisitedList.toArray(new Integer[0]);
	}
	
	@Override
	public boolean hasNext() {
		return nbCandidates > 0;
	}

	@Override
	public Integer next() {
		nbCandidates--;
		return candidates[nbCandidates];
	}

	@Override
	public void remove() {}

}
