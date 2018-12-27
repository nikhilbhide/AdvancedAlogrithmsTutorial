package com.nik.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.nik.tutorial.graph.shortestpath.BellmanFordAlgorithm;
import com.nik.tutorial.graph.shortestpath.Edge;
import com.nik.tutorial.graph.shortestpath.Vertex;

public class BellmanFordTest {
	private BellmanFordAlgorithm instance;
	@Before
	public void init() {
		instance = new BellmanFordAlgorithm();
	}

	@After
	public void tearDown() {
		instance = null;
	}

	@Test
	public void testBellmanFordSimpleGraphWithPositiveEdges() {
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

		List<Edge> edgeList = new ArrayList();
		edgeList.add(e1);
		edgeList.add(e2);
		edgeList.add(e3);
		edgeList.add(e4);
		
		instance.setEdgeList(edgeList);

		String[] traversalPath = {v1.getName(),v3.getName(),v4.getName()};
		Assert.assertTrue(Arrays.deepEquals(traversalPath, instance.getShortestPath("A","D").toArray()));
	}

	@Test
	public void testBellmanFordSimpleGraphWithNegativeEdges() {
		Vertex v1 = new Vertex("A");
		Vertex v2 = new Vertex("B");
		Vertex v3 = new Vertex("C");
		Vertex v4 = new Vertex("D");
		instance.getVertexMap().put("A", v1);
		instance.getVertexMap().put("B", v2);
		instance.getVertexMap().put("C", v3);
		instance.getVertexMap().put("D", v4);


		Edge e1 = new Edge(v1, v2, 3);
		Edge e2 = new Edge(v2, v3, -2);
		Edge e3 = new Edge(v3, v4, 1);
		Edge e4 = new Edge(v1, v4, 3);
		
		List<Edge> edgeList = new ArrayList();
		edgeList.add(e1);
		edgeList.add(e2);
		edgeList.add(e3);
		edgeList.add(e4);
		
		instance.setEdgeList(edgeList);

		String[] traversalPath = {v1.getName(),v2.getName(),v3.getName(),v4.getName()};
		Assert.assertTrue(Arrays.deepEquals(traversalPath, instance.getShortestPath("A","D").toArray()));
		Assert.assertTrue(v4.getDistance()==2);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testBellmanFordSimpleGraphConsistingCycleSuccess() {
		Vertex v1 = new Vertex("A");
		Vertex v2 = new Vertex("B");
		Vertex v3 = new Vertex("C");
		Vertex v4 = new Vertex("D");
		instance.getVertexMap().put("A", v1);
		instance.getVertexMap().put("B", v2);
		instance.getVertexMap().put("C", v3);
		instance.getVertexMap().put("D", v4);


		Edge e1 = new Edge(v1, v2, 1);
		Edge e2 = new Edge(v2, v3, -1);
		Edge e3 = new Edge(v3, v4, -1);
		Edge e4 = new Edge(v4, v1, -1);
		
		List<Edge> edgeList = new ArrayList();
		edgeList.add(e1);
		edgeList.add(e2);
		edgeList.add(e3);
		edgeList.add(e4);
		
		instance.setEdgeList(edgeList);

		instance.getShortestPath("A","D");
	}
	
	@Test
	public void testBellmanFordComplexGraph() {
		Vertex v1 = new Vertex("A");
		Vertex v2 = new Vertex("B");
		Vertex v3 = new Vertex("C");
		Vertex v4 = new Vertex("D");
		Vertex v5 = new Vertex("E");
		Vertex v6 = new Vertex("F");
		Vertex v7 = new Vertex("G");
		
		instance.getVertexMap().put("A", v1);
		instance.getVertexMap().put("B", v2);
		instance.getVertexMap().put("C", v3);
		instance.getVertexMap().put("D", v4);
		instance.getVertexMap().put("E", v5);
		instance.getVertexMap().put("F", v6);
		instance.getVertexMap().put("G", v7);


		Edge e1 = new Edge(v1, v2, 6);
		Edge e2 = new Edge(v1, v7, 5);
		Edge e3 = new Edge(v1, v3, 5);
		Edge e4 = new Edge(v2, v4,-1);
		Edge e5 = new Edge(v7, v2,-2);
		Edge e6 = new Edge(v3, v7,-2);
		Edge e7 = new Edge(v7, v4,1);
		Edge e8 = new Edge(v3, v5,-1);
		Edge e9 = new Edge(v5, v6,3);
		Edge e10 = new Edge(v4, v6,3);
		
		List<Edge> edgeList = new ArrayList();
		edgeList.add(e1);
		edgeList.add(e2);
		edgeList.add(e3);
		edgeList.add(e4);
		edgeList.add(e5);
		edgeList.add(e6);
		edgeList.add(e7);
		edgeList.add(e8);
		edgeList.add(e9);
		edgeList.add(e10);
		
		instance.setEdgeList(edgeList);
		String[] traversalPath = {v1.getName(),v3.getName(),v7.getName(),v2.getName(),v4.getName(),v6.getName()};
		Assert.assertTrue(Arrays.deepEquals(traversalPath, instance.getShortestPath("A","F").toArray()));
	}
}