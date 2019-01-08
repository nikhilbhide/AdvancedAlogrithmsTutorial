package com.nik.tutorial.graph.mst;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;

public class PrimsAlgorithm {

	private ArrayList<Node> visitedVertices;
	private ArrayList<Edge> mstEdges;
	private PriorityQueue<Edge> heapEdges;
	private ConcurrentHashMap<String, Node> vertexMap = new ConcurrentHashMap();

	public ConcurrentHashMap<String, Node> getVertexMap() {
		return vertexMap;
	}

	public void setVertexMap(ConcurrentHashMap<String, Node> vertexMap) {
		this.vertexMap = vertexMap;
	}

	public PrimsAlgorithm() {
		visitedVertices= new ArrayList();
		mstEdges = new ArrayList();
		heapEdges = new PriorityQueue<>();
	}	

	/**
	 * Create a spanning tree using Prim's algorithm
	 * 
	 * @param vertex The Starting vertex
	 * @return The list of edges 
	 */
	public ArrayList<Edge> createSpanningTree(Node vertex) {


		while(vertexMap.keySet().size()!=visitedVertices.size()) {
			visitedVertices.add(vertex);
			List<Edge> edges = vertex.getNeighbours();

			if(edges!=null) {
				for(Edge edge:edges) {
					Node destVertex = edge.getDestVertex();
					if(!visitedVertices.contains(destVertex)) {
						heapEdges.add(edge);
					}
				}
			}

			if(!heapEdges.isEmpty()) {
				Edge minEdge = heapEdges.remove();
				if(!visitedVertices.contains(minEdge.getDestVertex())) {
					vertex = minEdge.getDestVertex();
					mstEdges.add(minEdge);
				}
			}
		}
		return mstEdges;
	}
}