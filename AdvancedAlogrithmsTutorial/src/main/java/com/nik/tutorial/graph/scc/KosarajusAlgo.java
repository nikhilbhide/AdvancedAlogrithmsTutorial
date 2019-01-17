package com.nik.tutorial.graph.scc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.SerializationUtils;

import com.nik.tutorial.graph.shortestpath.Edge;
import com.nik.tutorial.graph.shortestpath.Vertex;

/**
 * This class provides implementation of Kosaraju's algorithm that finds strongly connected components in a provided graph.
 * A strongly connected component in the graph can be entire graph or a subpgraph in which all vertices are reachable from any vertex.
 * For an instance, if graph has 4 vertices then if you start traversing any vertex then all other vertices are reachable having a cycle.
 * This property is applicable to all other vertices.
 *  
 * @author nikhil.bhide
 *
 */
public class KosarajusAlgo {
	private ConcurrentHashMap<String, Vertex> vertexMap = new ConcurrentHashMap();

	public ConcurrentHashMap<String, Vertex> getVertexMap() {
		return vertexMap;
	}

	public void setVertexMap(ConcurrentHashMap<String, Vertex> vertexMap) {
		this.vertexMap = vertexMap;
	}

	/**
	 * Finds the strongly connected components in a graph. 
	 * The algorithm first finds the DFS order of graph. Once DFS is done, transpose of provided graph is created.
	 * Transposed algorithm is traversed in a DFS order based on the DFS order of an original graph.
	 * @return
	 */
	public ArrayList<ArrayList<Vertex>> findSCC() {
		Stack<Vertex> dfsOrder = getDFSTraversal();
		ConcurrentHashMap<String, Vertex> transposeGraphVertexMap = getTranspose();
		ArrayList<Vertex> visitedVertices = new ArrayList<Vertex>();
		ArrayList<ArrayList<Vertex>> sccSets = new ArrayList<ArrayList<Vertex>>();

		while(dfsOrder!=null && dfsOrder.size()!=0) {
			Vertex currentVertex = transposeGraphVertexMap.get(dfsOrder.pop().getName());
			if(!visitedVertices.contains(currentVertex)) {
				ArrayList<Vertex> sccSet = new ArrayList<Vertex>();
				sccSet.add(currentVertex);
				findSCC(currentVertex,sccSet, visitedVertices);
						sccSets.add(sccSet);
			}
		}

		return sccSets;
	}

	private void findSCC(Vertex currentVertex, ArrayList<Vertex> sccSet, ArrayList<Vertex> visitedVertices) {
		if(!visitedVertices.contains(currentVertex)) {
			visitedVertices.add(currentVertex);	

			for(Edge edge:currentVertex.getNeightbours()) {
				Vertex destVertex = edge.getDestination();
				findSCC(destVertex,sccSet,visitedVertices);
			}
		}
	}


	/**
	 * 
	 * @return
	 */
	public ConcurrentHashMap<String, Vertex> getTranspose() {
		ConcurrentHashMap<String, Vertex> transposeVertexMap = (ConcurrentHashMap<String, Vertex>) SerializationUtils.clone(vertexMap);
		Iterator<Entry<String,Vertex>> iterator = transposeVertexMap.entrySet().iterator();
		ArrayList<Edge> edges = new ArrayList();

		while(iterator.hasNext()) {
			Vertex currentVertex = iterator.next().getValue();
			edges.addAll(currentVertex.getNeightbours());
		}

		for(Edge edge:edges) {
			Vertex temp = edge.getDestination();
			edge.setDestinatio(edge.getSource());
			edge.setSource(temp);
			edge.getSource().getNeightbours().add(edge);
			edge.getDestination().getNeightbours().remove(edge);			
		}

		return transposeVertexMap;
	}

	/**
	 * Finds DFS order of a provided graph
	 * 
	 * @return The stack of vertices arranged in DFS order
	 */
	private Stack<Vertex> getDFSTraversal() {
		Stack<Vertex> dfsOrder = new Stack();
		ArrayList<Vertex> visitedVertices = new ArrayList<Vertex>();

		for(Vertex v: vertexMap.values()) {
			if(!dfsOrder.contains(v)) {
				getDFSTraversal(v,dfsOrder, visitedVertices);
			}
		}

		return dfsOrder;
	}

	/**
	 * Helper function to find DFS order 
	 * 
	 * @param v The vertex under traversal
	 * @param dfsOrder The traversal of vertices in DFS order
	 */
	private void getDFSTraversal(Vertex v, Stack<Vertex> dfsOrder, ArrayList<Vertex> visitedVertices) {
		if(!dfsOrder.contains(v) && !visitedVertices.contains(v)) {
			visitedVertices.add(v);
			for(Edge e: v.getNeightbours()) {
				if(!dfsOrder.contains(e.getDestination())) {
					getDFSTraversal(e.getDestination(),dfsOrder,visitedVertices);
				}
			}
			dfsOrder.push(v);
		}		
	}
}