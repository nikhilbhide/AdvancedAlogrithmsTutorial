package com.nik.tutorial.graph;

import java.util.concurrent.ConcurrentHashMap;

public class GraphUtility {
	/**
	 * Creates map of vertex name and {@link Vertex} object
	 * 
	 * @param vertices The array of vertices of graph
	 * @return vertexMap The map with vertextName {@link String} and vertexObject {@link Vertex}
	 */
	public static ConcurrentHashMap<String,Vertex> getVertexMap(String[] vertices) {
		ConcurrentHashMap<String,Vertex> vertexMap = null;
		if(vertices!=null) {
			vertexMap = new ConcurrentHashMap<String,Vertex>();
			for(String vertexName:vertices) {
				Vertex vertex = new  Vertex(vertexName);
				vertexMap.put(vertexName, vertex);
			}
		}
		return vertexMap;
	}

	/**
	 * Populates the edges of a vertex object based on edhe map provided
	 * 
	 * @param vertexMap The map with vertextName {@link String} and vertexObject {@link Vertex}
	 * @param edgeMap he map with vertextName {@link String} and edgeArray {@link String[]}
	 */
	public static void populateEdgeList(ConcurrentHashMap<String,Vertex> vertexMap,ConcurrentHashMap<String, String[]> edgeMap) {
		if(vertexMap!=null) {
			vertexMap.forEach((k,v)-> {
				String[] edgeVertices = edgeMap.get(k);
				for(String edgeVertex:edgeVertices) {
					v.getVertices().add(vertexMap.get(k));
				}
			});
		}
	}	
}
