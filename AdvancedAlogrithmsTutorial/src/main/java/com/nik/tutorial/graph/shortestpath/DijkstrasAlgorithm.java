package com.nik.tutorial.graph.shortestpath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * This class implements Dijkstra's shortest path algorithm 
 * 
 * @author nikhil.bhide
 *
 */
public class DijkstrasAlgorithm {

	private PriorityQueue<Vertex> vQueue = new PriorityQueue();
	private ConcurrentHashMap<String, Vertex> vertexMap = new ConcurrentHashMap();

	public PriorityQueue<Vertex> getvQueue() {
		return vQueue;
	}

	public void setvQueue(PriorityQueue<Vertex> vQueue) {
		this.vQueue = vQueue;
	}

	public ConcurrentHashMap<String, Vertex> getVertexMap() {
		return vertexMap;
	}

	public void setVertexMap(ConcurrentHashMap<String, Vertex> vertexMap) {
		this.vertexMap = vertexMap;
	}

	public boolean add(Vertex v) {
		return vQueue.add(v);
	}

	public boolean remove(Vertex v) {
		return vQueue.remove(v);
	}

	public Vertex getMin() {
		return vQueue.peek();
	}

	/**
	 * Finds the shortest path algorithm by backtracking the graph from destination order to source order.
	 * If distances for each of the vertex are not calculated then it calculates the distances.
	 * 
	 * @param startVertex The starting vertex of the shortest traversal
	 * @param destVertex The destination vertex of the shortest traversal
	 * 
	 * @return The list containing shortest path tree along the vertices
	 */
	public ArrayList<String> getShortestPath(String startVertexName, String destVertexName) {
		Vertex startingVertex = vertexMap.get(startVertexName);
		startingVertex.setDistance(0);
		ArrayList<Vertex> shortestPathTraversal = new ArrayList();

		if(startingVertex!=null) {
			PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
			queue.add(startingVertex);
			getShortestPath(queue);
		}
		
		Vertex destVertex = vertexMap.get(destVertexName);
		shortestPathTraversal.add(destVertex);

		while(!destVertex.getName().equals(startVertexName)) {
			destVertex = destVertex.getPredecesssor();
			shortestPathTraversal.add(destVertex);
		}
	
		Collections.reverse(shortestPathTraversal);
		return shortestPathTraversal.stream()
									.map(item->item.getName())
					                .collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * Distances for each of the vertex are not calculated then it calculates the distances.
	 * 
	 * @param queue This is priority queue which is based on min heap
	 */
	public void getShortestPath(PriorityQueue<Vertex> queue) {
		Vertex currentVertex = queue.remove();

		if(currentVertex!=null && !currentVertex.isVisited() & currentVertex.getNeightbours()!=null) {
			for(Edge edge:currentVertex.getNeightbours()) {
				Vertex destVertex = edge.getDestination();
				double totalEdgWeight = currentVertex.getDistance()+edge.getWeight();
				if(totalEdgWeight < destVertex.getDistance()) {
					destVertex.setDistance(totalEdgWeight);
					destVertex.setPredecesssor(currentVertex);
					queue.add(destVertex);
				}
			}
		}
		currentVertex.setVisited(true);
		if(queue.size()>0) {
			getShortestPath(queue);
		}
	}
}