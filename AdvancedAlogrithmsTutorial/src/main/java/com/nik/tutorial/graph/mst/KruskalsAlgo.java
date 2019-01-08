package com.nik.tutorial.graph.mst;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/**
 * This class provides the implements Kruskal's algorithm -  @see <a href="https://en.wikipedia.org/wiki/Kruskal%27s_algorithm">Kruskal's algorithm explaination on wiki</a>
 * Internally it uses disjoint sets and union-find algorithm to comput MST
 * 
 * @author nikhil.bhide
 *
 */
public class KruskalsAlgo {

	//The list of vertices of the graph
	private ArrayList<Node> vertices;

	//The list of edges of the graph
	private ArrayList<Edge> edges;

	//Representation of vertices in disjoint sets format
	private DisjointSets ds = new DisjointSets();

	public ArrayList<Node> getVertices() {
		return vertices;
	}

	public void setVertices(ArrayList<Node> vertices) {
		this.vertices = vertices;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}

	/**
	 * Creates a graph based on number of vertices and 
	 * @param numVertices The number of vertices
	 * 
	 * @return <code>true</code> if graph is created without any exception
	 */
	public boolean createGraph(ArrayList<Edge> edgeList) {
		Collections.sort(edgeList);
		vertices = new ArrayList<Node>();
		edges = edgeList;
		
		for(Edge edge:edgeList) {
			Node source = edge.getSourceVertex();
			Node destination = edge.getDestVertex();
			if(!vertices.contains(source)) {
				vertices.add(source);
			}
			if(!vertices.contains(destination)) {
				vertices.add(destination);
			}
		}
		
		ds.makeSet(vertices.size());
		return true;
	}

	/**
	 * Constructs minimum spanning tree
	 * 
	 * @return The list of edges of MST
	 */
	public HashSet<Edge> findMST() {
		HashSet<Edge> mstEdgesSet = new HashSet();	

		for(Edge edge:edges) {
			Node source = edge.getSourceVertex();
			Node dest = edge.getDestVertex();
			if(!ds.findSet(source).equals(ds.findSet(dest)) ) {
				ds.union(source, dest);
				mstEdgesSet.add(edge);
			}
		}
		
		return mstEdgesSet;
	}
}