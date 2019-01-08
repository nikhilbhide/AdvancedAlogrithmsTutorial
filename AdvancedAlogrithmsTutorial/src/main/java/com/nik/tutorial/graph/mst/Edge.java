package com.nik.tutorial.graph.mst;

import java.util.Comparator;

public class Edge implements Comparable<Edge> {
	
	public Edge(Node sourceVertex, Node destVertex, double weight) {
		this.sourceVertex = sourceVertex;
		this.destVertex = destVertex;
		this.weight = weight;
	}
	private Node sourceVertex;
	public Node getSourceVertex() {
		return sourceVertex;
	}
	public void setSourceVertex(Node sourceVertex) {
		this.sourceVertex = sourceVertex;
	}
	public Node getDestVertex() {
		return destVertex;
	}
	public void setDestVertex(Node destVertex) {
		this.destVertex = destVertex;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	private Node destVertex;
	private double weight;
	
	@Override
	public int compareTo(Edge otherEdge) {
		return Double.compare(this.getWeight(), otherEdge.getWeight());
	}
}