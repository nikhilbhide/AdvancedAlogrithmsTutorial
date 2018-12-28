package com.nik.tutorial.graph.shortestpath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge.
 * In every edge, vertex u comes before v in the ordering. 
 * Topological Sorting for a graph is not possible if the graph is not a DAG. 
 * 
 * @author nikhil.bhide
 */

public class TopologicalSorting {
	private ConcurrentHashMap<String, Vertex> vertexMap = new ConcurrentHashMap();
	private List<Edge> edgeList = new ArrayList();

	public List<Edge> getEdgeList() {
		return edgeList;
	}

	public void setEdgeList(List<Edge> edgeList) {
		this.edgeList = edgeList;
	}

	public ConcurrentHashMap<String, Vertex> getVertexMap() {
		return vertexMap;
	}

	public void setVertexMap(ConcurrentHashMap<String, Vertex> vertexMap) {
		this.vertexMap = vertexMap;
	}

	/**
	 * Figures out the vertices which have indegree equal to 0
	 * All of those vertices are topological vertices and should appear among first visted nodes in toplogical sorting
	 * 
	 * @param edgeList The list of the edges
	 * @param vertexMap The map containing pair of vertex name and vertex instance {@link Vertex}
	 * 
	 * @return The list containing topological vertices
	 */
	public ArrayList<String> retrieveToplogicalVertices(ArrayList<Edge> edgeList, ConcurrentHashMap<String, Vertex> vertexMap) {
		ArrayList<String> destinationVertices = edgeList.stream()
				.map(item->item.getDestination().getName())
				.collect(Collectors.toCollection(ArrayList::new));

		ArrayList<String> toplogicalVertices = vertexMap.keySet()
				.stream()
				.filter(vertex->!destinationVertices.contains(vertex))
				.collect(Collectors.toCollection(ArrayList::new));

		return toplogicalVertices;
	}


	/**
	 * Figures out the vertices which have indegree equal to 0
	 * All of those vertices are topological vertices and should appear among first visted nodes in toplogical sorting
	 * This method is overloaded version which does not accept any input parameter
	 * 
	 * @return The list containing topological vertices
	 */
	public ArrayList<String> retrieveToplogicalVertices() {
		ArrayList<String> destinationVertices = edgeList.stream()
				.map(item->item.getDestination().getName())
				.distinct()
				.collect(Collectors.toCollection(ArrayList::new));

		ArrayList<String> toplogicalVertices = vertexMap.keySet()
				.stream()
				.sorted()
				.filter(vertex->!destinationVertices.contains(vertex))
				.collect(Collectors.toCollection(ArrayList::new));

		return toplogicalVertices;
	}

	/**
	 * Traverses the graph in topological order. This is done in DFS mode however order may not resemble with DFS.
	 * In this traversal, all nodes with indegree equal to zero are traversed first and then only remaining nodes are traversed in DFS mode.
	 * 
	 * @return The list of vertices in topological order
	 */
	public ArrayList<String> topologicaltraverse() {
		ArrayList<String> topologicalOrderVertices = retrieveToplogicalVertices();
		Stack<Vertex> tempStack = new Stack();

		//iterate over topological vertices
		vertexMap.entrySet()
		.stream()
		.forEach(entry-> {
			topologicaltraverse(entry.getValue(),tempStack);
		});

		//push topological vertices on the temp stack
		topologicalOrderVertices.forEach(vertexName-> {
			tempStack.push(vertexMap.get(vertexName));
		});

		//retreive the vertices of topological sort/traversal
		ArrayList<String> result = new ArrayList();
		while(tempStack.size()>0) {
			Vertex vertex = tempStack.pop();
			if(!result.contains(vertex.getName())) {
				result.add(vertex.getName());
			}
		}
		
		return result;
	}

	/**
	 * Traverses the graph in topological order. This is done in DFS mode however order may not resemble with DFS.
	 * In this traversal, all nodes which are descendants of topological nodes (nodes having indegree equal to 0) are traversed in DFS mode.
	 * 
	 * @param currentVertex The current vertex 
	 * @param tempStack The stack that holds the vertices visited in topological order
	 * 
	 */
	public void topologicaltraverse(Vertex currentVertex, Stack tempStack) {
		if(!currentVertex.isVisited()) {
			if(currentVertex.getNeightbours()!=null) {
				for(Edge edge:currentVertex.getNeightbours()) {
					topologicaltraverse(edge.getDestination(), tempStack);
				}
			}
			currentVertex.setVisited(true);
			if(!tempStack.contains(currentVertex)) {
				tempStack.push(currentVertex);
			}
		}
	}
}