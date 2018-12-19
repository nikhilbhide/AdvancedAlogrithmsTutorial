package com.nik.test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.nik.tutorial.graph.Graph;
import com.nik.tutorial.graph.GraphUtility;
import com.nik.tutorial.graph.Vertex;

public class GraphTraversalTest {
	private Graph graphInstance;

	@Before
	public void setUp() {
		graphInstance = new Graph();
	}

	@After
	public void tearDown() {
		graphInstance = null;
	}

	@Test
	public void testBFSTraveseEmptyGraphSuccess() {
		Assert.assertTrue(graphInstance.traverseBFS("").isEmpty());
	}

	@Test
	public void testBFSTraveseSimpleGraphSuccess() {
		String[] verticeValues = {"A","B","C"};
		String[] edgesVertexA = {"B","C"};
		String[] edgesVertexB = {"C"};
		ConcurrentHashMap<String,String[]> edgeMap = (ConcurrentHashMap<String, String[]>) new ConcurrentHashMap();
		edgeMap.put("A", edgesVertexA);
		edgeMap.put("B",edgesVertexB);
		ConcurrentHashMap<String, Vertex> vertexMap = GraphUtility.getVertexMap(verticeValues);
		graphInstance.createGraph(vertexMap, edgeMap);
		String[] vertices = {"A","B","C"};
		List<String> verticesList = Arrays.asList(vertices);
		Assert.assertArrayEquals(vertices, graphInstance.traverseBFS("A").toArray());
	}
	
	@Test
	public void testBFSTraveseComplexGraphSuccess() {
		String[] verticeValues = {"A","B","C","D","E","F","G"};
		String[] edgesVertexA = {"B","C"};
		String[] edgesVertexB = {"G","D"};
		String[] edgesVertexC = {"D"};
		String[] edgesVertexD = {"E"};
		String[] edgesVertexE = {"F"};
		String[] edgesVertexF = {"B"};
		String[] edgesVertexG = {"D","E"};
		
		ConcurrentHashMap<String,String[]> edgeMap = (ConcurrentHashMap<String, String[]>) new ConcurrentHashMap();
		edgeMap.put("A", edgesVertexA);
		edgeMap.put("B",edgesVertexB);
		edgeMap.put("C",edgesVertexC);
		edgeMap.put("D",edgesVertexD);
		edgeMap.put("E",edgesVertexE);
		edgeMap.put("F",edgesVertexF);
		edgeMap.put("G",edgesVertexG);
		ConcurrentHashMap<String, Vertex> vertexMap = GraphUtility.getVertexMap(verticeValues);
		graphInstance.createGraph(vertexMap, edgeMap);
		String[] vertices = {"A","B","C","G","D","E","F"};
		List<String> verticesList = Arrays.asList(vertices);
		Assert.assertArrayEquals(vertices, graphInstance.traverseBFS("A").toArray());
	}
}
