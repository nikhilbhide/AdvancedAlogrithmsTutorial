package com.nik.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.nik.tutorial.graph.scc.KosarajusAlgo;
import com.nik.tutorial.graph.shortestpath.Edge;
import com.nik.tutorial.graph.shortestpath.Vertex;

public class KosarajusAlgoTest {
	private KosarajusAlgo instance;

	@Before
	public void setUp() {
		instance = new KosarajusAlgo();
	}

	@After
	public void tearDown() {
		instance = null;
	}

	//	@Test
	public void testTransposeOfGraphSuccess() {
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

		Edge e1 = new Edge(v1, v2, 1);
		Edge e2 = new Edge(v1, v3, 1);
		Edge e3 = new Edge(v2, v1, 1);
		Edge e4 = new Edge(v2, v3,1);
		Edge e5 = new Edge(v3, v2,1);
		Edge e6 = new Edge(v3, v4,1);
		Edge e7 = new Edge(v4, v5,1);
		Edge e8 = new Edge(v5, v4,1);
		Edge e9 = new Edge(v5, v6,1);
		Edge e10 = new Edge(v6, v5,1);
		Edge e11 = new Edge(v1, v7,1);
		Edge e12 = new Edge(v2, v7,1);


		v1.getNeightbours().add(e1);
		v1.getNeightbours().add(e2);
		v1.getNeightbours().add(e11);

		v2.getNeightbours().add(e3);
		v2.getNeightbours().add(e4);
		v2.getNeightbours().add(e12);

		v3.getNeightbours().add(e5);
		v3.getNeightbours().add(e6);
		v4.getNeightbours().add(e7);
		v5.getNeightbours().add(e8);
		v5.getNeightbours().add(e9);
		v6.getNeightbours().add(e10);

		ConcurrentHashMap<String, Vertex> transposeVertexMap = instance.getTranspose();
		Vertex vertexA = transposeVertexMap.get("A");
		Assert.assertTrue(vertexA.getNeightbours().size()==1);

		Vertex vertexB = transposeVertexMap.get("B");
		Assert.assertTrue(vertexB.getNeightbours().size()==2);

		Vertex vertexG = transposeVertexMap.get("G");
		Assert.assertTrue(vertexG.getNeightbours().size()==2);
	}

	@Test
	public void testSCCSuccess() {
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

		Edge e1 = new Edge(v1, v2, 1);
		Edge e2 = new Edge(v1, v3, 1);
		Edge e3 = new Edge(v2, v1, 1);
		Edge e4 = new Edge(v2, v3,1);
		Edge e5 = new Edge(v3, v2,1);
		Edge e6 = new Edge(v3, v4,1);
		Edge e7 = new Edge(v4, v5,1);
		Edge e8 = new Edge(v5, v4,1);
		Edge e9 = new Edge(v5, v6,1);
		Edge e10 = new Edge(v6, v5,1);
		Edge e11 = new Edge(v6, v7,1);
		Edge e12 = new Edge(v7, v6,1);


		v1.getNeightbours().add(e1);
		v1.getNeightbours().add(e2);

		v2.getNeightbours().add(e3);
		v2.getNeightbours().add(e4);

		v3.getNeightbours().add(e5);
		v3.getNeightbours().add(e6);

		v4.getNeightbours().add(e7);

		v5.getNeightbours().add(e8);
		v5.getNeightbours().add(e9);

		v6.getNeightbours().add(e10);
		v6.getNeightbours().add(e11);

		v7.getNeightbours().add(e12);

		ArrayList<ArrayList<Vertex>> sccSets = instance.findSCC();
		Assert.assertTrue(sccSets.size()==2);

		ArrayList<Vertex> sccSet1 = sccSets.get(0);
		ArrayList<Vertex> sccSet2 = sccSets.get(1);

		List<String> actualVerticesSet1 = sccSet1.stream()
				.map(vertex-> vertex.getName())
				.sorted()
				.collect(Collectors.toList());	
		
		List<String> expectedVerticesSet1 = Arrays.asList("A","B","C");
		
		Assert.assertTrue(Arrays.deepEquals(expectedVerticesSet1.toArray(),actualVerticesSet1.toArray()));
		
		List<String> actualVerticesSet2 = sccSet2.stream()
				.map(vertex-> vertex.getName())
				.sorted()
				.collect(Collectors.toList());	
		
		List<String> expectedVerticesSet2 = Arrays.asList("D","E","F","G");
		
		Assert.assertTrue(Arrays.deepEquals(expectedVerticesSet2.toArray(),actualVerticesSet2.toArray()));

	}	
}