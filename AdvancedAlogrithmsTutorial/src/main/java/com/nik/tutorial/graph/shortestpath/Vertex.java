package com.nik.tutorial.graph.shortestpath;

import java.util.List;

/**
 * This class represents the Vertex entity of the graph data structure
 * 
 * @author nikhil.bhide
 *
 */

public class Vertex implements Comparable<Vertex> {
	private String name;
	private List<Edge> neightbours;
	private boolean visited;
	private double distance = Double.MAX_VALUE;
	private Vertex predecesssor = null;
	
	public Vertex(String name) {
		super();
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Edge> getNeightbours() {
		return neightbours;
	}
	public void setNeightbours(List<Edge> neightbours) {
		this.neightbours = neightbours;
	}
	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public Vertex getPredecesssor() {
		return predecesssor;
	}
	public void setPredecesssor(Vertex predecesssor) {
		this.predecesssor = predecesssor;
	}
	
	@Override
	public int compareTo(Vertex otherVertex) {
	return Double.compare(this.getDistance(), otherVertex.getDistance());
	}
}