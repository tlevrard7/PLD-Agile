package com.Bestanome.Model.Outils.TSP;

import java.util.Collection;

public class TSP2 extends TSP1 {
	@Override
	protected int bound(Integer currentVertex, Collection<Integer> unvisited) {
		int bound = 0;
	
		int minOutgoing = Integer.MAX_VALUE;
		for (int node : unvisited) {
			if (g.isArc(currentVertex, node)) {
				minOutgoing = Math.min(minOutgoing, g.getCost(currentVertex, node));
			}
		}
		bound += (minOutgoing == Integer.MAX_VALUE ? 0 : minOutgoing);
	
		for (int node : unvisited) {
			int minEdge = Integer.MAX_VALUE;
			for (int other : unvisited) {
				if (node != other && g.isArc(node, other)) {
					minEdge = Math.min(minEdge, g.getCost(node, other));
				}
			}
			bound += (minEdge == Integer.MAX_VALUE ? 0 : minEdge);
		}
	
		int returnCost = Integer.MAX_VALUE;
		for (int node : unvisited) {
			if (g.isArc(node, 0)) {
				returnCost = Math.min(returnCost, g.getCost(node, 0));
			}
		}
		bound += (returnCost == Integer.MAX_VALUE ? 0 : returnCost);
	
		return bound;
	}

}
