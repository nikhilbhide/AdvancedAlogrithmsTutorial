package com.nik.tutorial.graph.shortestpath;

import java.io.Serializable;

import com.nik.tutorial.graph.shortestpath.Vertex;

/**
 * This class provides the implementation of edge entity.
 * 
 * @author nikhil.bhide
 */
public class Edge implements Comparable<Edge>, Serializable {
	private Vertex source;
	private Vertex destination;
	private double weight;

	public Edge(Vertex source, Vertex destination, double weight) {
		super();
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}

	public Vertex getSource() {
		return source;
	}
	public void setSource(Vertex source) {
		this.source = source;
	}
	public Vertex getDestination() {
		return destination;
	}
	public void setDestinatio(Vertex destination) {
		this.destination = destination;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;

	}

	@Override
	public int compareTo(Edge otherEdge) {
		return Double.compare(this.getWeight(),otherEdge.getWeight());
	}
}