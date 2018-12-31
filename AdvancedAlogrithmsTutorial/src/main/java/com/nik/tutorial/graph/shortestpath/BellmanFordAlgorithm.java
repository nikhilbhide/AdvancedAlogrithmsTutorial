package com.nik.tutorial.graph.shortestpath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import com.nik.tutorial.graph.shortestpath.Vertex;

/**
 * This class implements Bellman Ford algorithm
 * 
 * @author nikhil.bhide
 *
 */
public class BellmanFordAlgorithm {

	private ConcurrentHashMap<String, Vertex> vertexMap = new ConcurrentHashMap();
	private List<Edge> edgeList = new ArrayList();

	public ConcurrentHashMap<String, Vertex> getVertexMap() {
		return vertexMap;
	}

	public void setVertexMap(ConcurrentHashMap<String, Vertex> vertexMap) {
		this.vertexMap = vertexMap;
	}

	public List<Edge> getEdgeList() {
		return edgeList;
	}

	public void setEdgeList(List<Edge> edgeList) {
		this.edgeList = edgeList;
	}

	/**
	 * Finds the shortest path by relaxing each of the edge of the graph.
	 * After |V|*|E-1| iterations, check whether negative cycle exists.
	 * Bellman ford algorithm can not handle negative cycle case. However it can handle the negative weights unlike Dijkstra's algorithm.
	 * 
	 * @param startVertex The starting vertex of the shortest traversal
	 * @param destVertex The destination vertex of the shortest traversal
	 * 
	 * @return The list containing shortest path tree along the vertices
	 * @throws IllegalArgumentException In case graph has negative cycle
	 */
	public ArrayList<String> getShortestPath(String startVertexName, String destVertexName) {
		Vertex startingVertex = vertexMap.get(startVertexName);
		if(startingVertex!=null) {
			startingVertex.setDistance(0);	

			//relaxation phase
			for(int counter=0; counter<vertexMap.keySet().size()-1;counter++) {
				for(Edge edge:edgeList) {
					Vertex sourceVertex = edge.getSource();
					Vertex destVertex = edge.getDestination();
					double totalEdgWeight = sourceVertex.getDistance()+edge.getWeight();
					if(totalEdgWeight < destVertex.getDistance()) {
						destVertex.setDistance(totalEdgWeight);
						destVertex.setPredecesssor(sourceVertex);
					}
				}
			}

			//find negative cycle and throw exc
			if(isNegativeCycle(edgeList)) {
				throw new IllegalArgumentException("Graph contains negative cycles");
			}

			return shortestPathTraversal(startVertexName, destVertexName);
		}

		return null;
	}

	/**
	 * Provides the shortest path based on the distances calculated using Bellman ford
	 * @param startVertexName The starting vertex of the traversal
	 * @param destVertexName The destination vertex of the traversal
	 * 
	 * @return {@link List} containing the vertices along the shortest path traversal
	 */
	private ArrayList<String> shortestPathTraversal(String startVertexName, String destVertexName) {
		ArrayList<Vertex> shortestPathTraversal = new ArrayList();

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
	 * Detects a negative cycle in the graph
	 * 
	 * @param edgeList The list of edges 
	 * @return <code>true</code> if negative cycle is detected otherwise false
	 */
	private boolean isNegativeCycle(List<Edge> edgeList) {
		for(Edge edge:edgeList) {
			Vertex sourceVertex = edge.getSource();
			Vertex destVertex = edge.getDestination();
			if(destVertex.getDistance()!=Double.MAX_VALUE) {
				double totalEdgWeight = sourceVertex.getDistance()+edge.getWeight();
				if(totalEdgWeight < destVertex.getDistance()) {
					return true;
				}
			}
		}
		return false;
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

	/**
	 * Finds the longest path by relaxing each of the edge of the graph.
	 * After |V|*|E-1| iterations, check whether negative cycle exists.
	 * Bellman ford algorithm can not handle negative cycle case. However it can handle the negative weights unlike Dijkstra's algorithm.
	 * It differs from shortest path only in aspect. It negates all edges and then calls the bellman ford algorithm
	 * 
	 * @param startVertexName The starting vertex of the longest traversal
	 * @param destVertexName The destination vertex of the longest traversal
	 * 
	 * @return The list containing shortest path tree along the vertices
	 * @throws IllegalArgumentException In case graph has negative cycle
	 */
	public ArrayList<String> getLongestPath(String startVertexName, String destVertexName) {
		for(Edge edge:edgeList) {
			if(edge.getWeight()>0) {
				edge.setWeight(edge.getWeight()*-1);
			}
		}

		Vertex startingVertex = vertexMap.get(startVertexName);
		if(startingVertex!=null) {
			startingVertex.setDistance(0);	

			//relaxation phase
			for(int counter=0; counter<vertexMap.keySet().size()-1;counter++) {
				for(Edge edge:edgeList) {
					Vertex sourceVertex = edge.getSource();
					Vertex destVertex = edge.getDestination();
					double totalEdgWeight = sourceVertex.getDistance()+edge.getWeight();
					if(totalEdgWeight < destVertex.getDistance()) {
						destVertex.setDistance(totalEdgWeight);
						destVertex.setPredecesssor(sourceVertex);
					}
				}
			}	
		}
		return shortestPathTraversal(startVertexName, destVertexName);
	}
}