package com.nik.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.nik.tutorial.graph.shortestpath.DijkstrasAlgorithm;
import com.nik.tutorial.graph.shortestpath.Edge;
import com.nik.tutorial.graph.shortestpath.Vertex;

public class DijkstrasAlogrithmTest {

	private DijkstrasAlgorithm instance;
	@Before
	public void init() {
		instance = new DijkstrasAlgorithm();
	}

	@Test
	public void testPriorityQueue() {
		Vertex v1 = new Vertex("A");
		Vertex v2 = new Vertex("B");
		Vertex v3 = new Vertex("C");
		Vertex v4 = new Vertex("D");

		v1.setDistance(10);
		v2.setDistance(5);
		v3.setDistance(3);
		v4.setDistance(12);


		instance.add(v1);
		instance.add(v2);
		instance.add(v3);
		instance.add(v4);

		Assert.assertEquals("C",instance.getMin().getName());
	}

	@Test
	public void testShortestPathSimplgeGraph() {
		Vertex v1 = new Vertex("A");
		Vertex v2 = new Vertex("B");
		Vertex v3 = new Vertex("C");
		Vertex v4 = new Vertex("D");
		instance.getVertexMap().put("A", v1);
		instance.getVertexMap().put("B", v2);
		instance.getVertexMap().put("C", v3);
		instance.getVertexMap().put("D", v4);

		
		
		Edge e1 = new Edge(v1, v2, 5);
		Edge e2 = new Edge(v1, v3, 2);
		Edge e3 = new Edge(v2, v4, 1);
		Edge e4 = new Edge(v3, v4, 3);

		List<Edge> edgesA = new ArrayList();
		edgesA.add(e1);
		edgesA.add(e2);
		v1.setNeightbours(edgesA);

		List<Edge> edgesB = new ArrayList();
		edgesB.add(e3);
		v2.setNeightbours(edgesB);

		List<Edge> edgesC = new ArrayList();
		edgesC.add(e4);
		v3.setNeightbours(edgesC);
		

		String[] traversalPath = {v1.getName(),v3.getName(),v4.getName()};
		Assert.assertTrue(Arrays.deepEquals(traversalPath, instance.getShortestPath("A","D").toArray()));
	}
}