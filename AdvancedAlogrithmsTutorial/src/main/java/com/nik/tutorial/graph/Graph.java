package com.nik.tutorial.graph;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class is implementation of Graph.
 * It provides implementation of different apis of Graph such as BFS, DFS, Spanning Tree etc.
 * 
 * @author nikhil.bhide
 *
 */
public class Graph {
	private ConcurrentHashMap<String, String[]> edgeMap;
	private ConcurrentHashMap<String, Vertex> vertexMap;

	public Graph () {
		edgeMap = new ConcurrentHashMap();
		vertexMap = new ConcurrentHashMap();
	}

	
	/**
	 * Populates the graph based on details of vertices and edges
	 * 
	 * @param vertexMap The map of vertexname and vertex object
	 * @param edgeMap The map of vertex name and edgelist
	 */
	public void createGraph(ConcurrentHashMap<String,Vertex> vertexMap, ConcurrentHashMap<String, String[]> edgeMap) {
		this.edgeMap = edgeMap;
		this.vertexMap = vertexMap;
		
		edgeMap.forEach((k,v)-> {
			Vertex vertex = vertexMap.get(k);
			for(String vertexName:v) {
				Vertex neighbour = vertexMap.get(vertexName);
				vertex.getVertices().add(neighbour);
			}
		});
	}

	/**
	 * Traverses graph in breadth first order. 
	 * 
	 * @param queue The queue of visited nodes. We require a datastructure that can maintain the order of nodes in FIFO
	 * @param result The sequential hash set that contains the result of the nodes visited
	 */
	public void traverseBFS(Queue<Vertex> queue, LinkedHashSet<String> result) {
		Vertex visited = queue.remove();
		List<Vertex> neighboringVertices = visited.getVertices();
		result.add(visited.getName());

		for(Vertex vertex:neighboringVertices) {
			if(!result.contains(vertex.getName())) {
				queue.add(vertex);
			}
		}
		
		if(!queue.isEmpty()) {
			traverseBFS(queue, result);
		}
	}

	/**
	 * Traverses graph in breadth first order. 
	 * 
	 * @param startVertex The starting vertex of a traversal
	 */
	public LinkedHashSet <String> traverseBFS(String startVertex) {
		Vertex vertex = vertexMap.get(startVertex);
		LinkedHashSet <String> result = new LinkedHashSet ();

		if(vertex!=null) {
			Queue<Vertex> queue = new LinkedList();
			queue.add(vertex);
			result.add(vertex.getName());
			traverseBFS(queue, result);
		}
		return result;
	}
}