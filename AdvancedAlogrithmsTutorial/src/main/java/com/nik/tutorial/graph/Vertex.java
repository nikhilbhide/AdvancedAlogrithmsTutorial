package com.nik.tutorial.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * Graph has two main elements. First is Vertex and second is Edge. 
 * This class is the implementation of Vertex entity. This class is also useful in building the edge list.
 * Also, in case of DFS and BFS traversals this class is used. 
 * Vertex object has three attributes
 * 1. name - Its the name of the vertex
 * 2. vertices - Its the list of the connected nodes. In turn it provides the details of source and destination
 * 3. visited - Its the flag which is used in traversal algorithms to keep track of vertices visited during traversal. 
 * 
 * @author nikhil.bhide
 *
 */
public class Vertex {
	private String name;
	List<Vertex> vertices;
	boolean visited = false;

	/**
	 * Parameterized constructor for Vertex.
	 * The vertex instance requires name and list of connected components (vertices). 
	 * 	  
	 * @param name The name of the vertex
	 * @param vertices The list of connected nodes. Its the edge list.
	 * 
	 */
	public Vertex (String name, Vertex... vertices) {
		this.name = name;
		this.vertices = new LinkedList<Vertex>();
		for(Vertex vertex:vertices) {
			this.vertices.add(vertex);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Vertex> getVertices() {
		return vertices;
	}

	public void setVertices(List<Vertex> vertices) {
		this.vertices = vertices;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
}