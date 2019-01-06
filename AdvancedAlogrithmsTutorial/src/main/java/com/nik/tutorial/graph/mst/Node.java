package com.nik.tutorial.graph.mst;

import java.util.List;

import com.nik.tutorial.graph.shortestpath.Edge;

/**
 * This class represents Vertex in a graph and its specifically tailored for minimum spanning tree.
 * 
 * @author nikhil.bhide
 *
 */
public class Node {

	//Instance variable that points to parent node
	private Node parent;

	//Depth of the tree. This variable is relevant only in case of root node
	private int rank;

	//This instance variable holds the data value
	private int data;
	
	//This is a list of neighbours
	private List<Edge> neighbours;


	public List<Edge> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(List<Edge> neighbours) {
		this.neighbours = neighbours;
	}

	public Node(int data) {
		this.parent = this;
		this.data = data;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}
}