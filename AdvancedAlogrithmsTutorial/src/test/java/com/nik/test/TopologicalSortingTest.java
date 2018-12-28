package com.nik.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.nik.tutorial.graph.shortestpath.Vertex;
import com.nik.tutorial.graph.shortestpath.BellmanFordAlgorithm;
import com.nik.tutorial.graph.shortestpath.Edge;
import com.nik.tutorial.graph.shortestpath.TopologicalSorting;

public class TopologicalSortingTest {
	private TopologicalSorting instance;

	@Before
	public void init() {
		instance = new TopologicalSorting();
	}

	@After
	public void tearDown() {
		instance = null;
	}

	@Test
	public void testTopologicalVerticesSimpleSuccess() {
		Vertex v1 = new Vertex("A");
		Vertex v2 = new Vertex("B");
		Vertex v3 = new Vertex("C");
		Vertex v4 = new Vertex("D");
		
		instance.getVertexMap().put("A", v1);
		instance.getVertexMap().put("B", v2);
		instance.getVertexMap().put("C", v3);
		instance.getVertexMap().put("D", v4);


		Edge e1 = new Edge(v2, v3, 5);
		Edge e2 = new Edge(v1, v4, 2);
		Edge e3 = new Edge(v3, v4, 1);

		List<Edge> edgeList = new ArrayList<Edge>();
		edgeList.add(e1);
		edgeList.add(e2);
		edgeList.add(e3);
		
		instance.setEdgeList(edgeList);
		List<Edge> edgesA = new ArrayList();
		edgesA.add(e2);
		v1.setNeightbours(edgesA);

		List<Edge> edgesB = new ArrayList();
		edgesB.add(e1);
		v2.setNeightbours(edgesB);

		List<Edge> edgesC = new ArrayList();
		edgesC.add(e3);
		v3.setNeightbours(edgesC);
		ArrayList<String> actualTopologicalSortedList = instance.retrieveToplogicalVertices();
		
		String[] expectedTopologicalSortedList = {v1.getName(),v2.getName()};
		Assert.assertTrue(Arrays.deepEquals(expectedTopologicalSortedList, actualTopologicalSortedList.toArray()));
	}
	
	//@Test
	public void testTopologicalOrderSimpleGraphSuccess() {
		Vertex v1 = new Vertex("A");
		Vertex v2 = new Vertex("B");
		Vertex v3 = new Vertex("C");
		Vertex v4 = new Vertex("D");
		
		instance.getVertexMap().put("A", v1);
		instance.getVertexMap().put("B", v2);
		instance.getVertexMap().put("C", v3);
		instance.getVertexMap().put("D", v4);


		Edge e1 = new Edge(v2, v3, 5);
		Edge e2 = new Edge(v1, v4, 2);
		Edge e3 = new Edge(v3, v4, 1);

		List<Edge> edgeList = new ArrayList<Edge>();
		edgeList.add(e1);
		edgeList.add(e2);
		edgeList.add(e3);
		
		instance.setEdgeList(edgeList);
		List<Edge> edgesA = new ArrayList();
		edgesA.add(e2);
		v1.setNeightbours(edgesA);

		List<Edge> edgesB = new ArrayList();
		edgesB.add(e1);
		v2.setNeightbours(edgesB);

		List<Edge> edgesC = new ArrayList();
		edgesC.add(e3);
		v3.setNeightbours(edgesC);
		
		ArrayList<String> actualTopologicalSortedList = instance.topologicaltraverse();
		String[] expectedTopologicalSortedList = {v1.getName(),v2.getName(),v4.getName(),v3.getName()};
		Assert.assertTrue(Arrays.deepEquals(expectedTopologicalSortedList, actualTopologicalSortedList.toArray()));
	}
	
	@Test
	public void testTopologicalOrderComplexGraphSuccess() {
		Vertex v1 = new Vertex("142");
		Vertex v2 = new Vertex("143");
		Vertex v3 = new Vertex("321");
		Vertex v4 = new Vertex("341");
		Vertex v5 = new Vertex("370");
		Vertex v6 = new Vertex("378");
		Vertex v7 = new Vertex("322");
		Vertex v8 = new Vertex("326");
		Vertex v9 = new Vertex("401");
		Vertex v10 = new Vertex("421");
		
		instance.getVertexMap().put("142", v1);
		instance.getVertexMap().put("143", v2);
		instance.getVertexMap().put("321", v3);
		instance.getVertexMap().put("341", v4);
		instance.getVertexMap().put("370", v5);
		instance.getVertexMap().put("378", v6);
		instance.getVertexMap().put("322", v7);
		instance.getVertexMap().put("326", v8);
		instance.getVertexMap().put("401", v9);
		instance.getVertexMap().put("421", v10);


		Edge e1 = new Edge(v1, v2, 5);
		Edge e2 = new Edge(v2, v3, 2);
		Edge e3 = new Edge(v2, v5, 1);
		Edge e4 = new Edge(v2, v6, 1);
		Edge e5 = new Edge(v2, v4, 1);
		Edge e6 = new Edge(v3, v7, 1);
		Edge e7 = new Edge(v3, v8, 1);
		Edge e8 = new Edge(v4, v9, 1);
		Edge e9 = new Edge(v7,v10,1);
		Edge e10 = new Edge(v7, v9, 1);
		Edge e11 = new Edge(v8, v9, 1);
		Edge e12 = new Edge(v8, v10, 1);
		
		List<Edge> edgeList = new ArrayList<Edge>();
		edgeList.add(e1);
		edgeList.add(e2);
		edgeList.add(e3);
		edgeList.add(e3);
		edgeList.add(e4);
		edgeList.add(e5);
		edgeList.add(e6);
		edgeList.add(e7);
		edgeList.add(e8);
		edgeList.add(e9);
		edgeList.add(e10);
		edgeList.add(e11);
		edgeList.add(e12);
	
		
		instance.setEdgeList(edgeList);
		List<Edge> edges142 = new ArrayList();
		edges142.add(e1);
		v1.setNeightbours(edges142);

		List<Edge> edges143 = new ArrayList();
		edges143.add(e2);
		edges143.add(e3);
		edges143.add(e4);
		edges143.add(e5);
		v2.setNeightbours(edges143);

		List<Edge> edges321 = new ArrayList();
		edges321.add(e6);
		edges321.add(e7);
		v3.setNeightbours(edges321);
		
		List<Edge> edges341 = new ArrayList();
		edges321.add(e8);
		v4.setNeightbours(edges341);
		
		List<Edge> edges322 = new ArrayList();
		edges322.add(e9);
		edges322.add(e10);
		v7.setNeightbours(edges322);
	
		List<Edge> edges326 = new ArrayList();
		edges326.add(e11);
		edges326.add(e12);
		v8.setNeightbours(edges326);
		
		ArrayList<String> actualTopologicalSortedList = instance.topologicaltraverse();
		String[] expectedTopologicalSortedList = {v1.getName(),v2.getName(),v4.getName(),v6.getName(), v5.getName(), v3.getName(), v8.getName(), v7.getName(), v9.getName(), v10.getName()};
		Assert.assertTrue(Arrays.deepEquals(expectedTopologicalSortedList, actualTopologicalSortedList.toArray()));
	}
}