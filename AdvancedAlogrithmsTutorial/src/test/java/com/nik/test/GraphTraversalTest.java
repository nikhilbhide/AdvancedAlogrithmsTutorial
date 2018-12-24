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
	
	@Test
	public void testBFSTraveseComplexGraphSuccessWithDifferentStartingVertex() {
		String[] verticeValues = {"A","B","C","D","E","F","G"};
		String[] edgesVertexA = {"B","C"};
		String[] edgesVertexB = {"A","G","D"};
		String[] edgesVertexC = {"D"};
		String[] edgesVertexD = {"E"};
		String[] edgesVertexE = {"F"};
		String[] edgesVertexF = {"G"};
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
		String[] vertices = {"B","A","G","D","C","E","F"};
		List<String> verticesList = Arrays.asList(vertices);
		Assert.assertArrayEquals(vertices, graphInstance.traverseBFS("B").toArray());
	}
	
	@Test
	public void testDFSTraveseSimpleGraphSuccess() {
		String[] verticeValues = {"A","B","C"};
		String[] edgesVertexA = {"B","C"};
		String[] edgesVertexB = {"C"};
		ConcurrentHashMap<String,String[]> edgeMap = (ConcurrentHashMap<String, String[]>) new ConcurrentHashMap();
		edgeMap.put("A", edgesVertexA);
		edgeMap.put("B",edgesVertexB);
		ConcurrentHashMap<String, Vertex> vertexMap = GraphUtility.getVertexMap(verticeValues);
		graphInstance.createGraph(vertexMap, edgeMap);
		String[] vertices = {"C","B", "A"};
		List<String> verticesList = Arrays.asList(vertices);
		Assert.assertArrayEquals(vertices, graphInstance.traverseDFS("A").toArray());
	}

	@Test
	public void testDFSTraveseComplexGraphSuccess() {
		String[] verticeValues = {"A","B","C","D","E","F","G"};
		String[] edgesVertexA = {"B","C"};
		String[] edgesVertexB = {"G","D"};
		String[] edgesVertexC = {"D"};
		String[] edgesVertexD = {"E"};
		String[] edgesVertexE = {"F"};
		String[] edgesVertexF = {"G"};
		
		ConcurrentHashMap<String,String[]> edgeMap = (ConcurrentHashMap<String, String[]>) new ConcurrentHashMap();
		edgeMap.put("A", edgesVertexA);
		edgeMap.put("B",edgesVertexB);
		edgeMap.put("C",edgesVertexC);
		edgeMap.put("D",edgesVertexD);
		edgeMap.put("E",edgesVertexE);
		edgeMap.put("F",edgesVertexF);
		ConcurrentHashMap<String, Vertex> vertexMap = GraphUtility.getVertexMap(verticeValues);
		graphInstance.createGraph(vertexMap, edgeMap);
		String[] vertices = {"G","F","E","D","B","C","A"};
		List<String> verticesList = Arrays.asList(vertices);
		Assert.assertArrayEquals(vertices, graphInstance.traverseDFS("A").toArray());
	}
	
	@Test
	public void testDFSTraveseComplexGraphWithCycleSuccess() {
		String[] verticeValues = {"A","B","C","D","E","F","G"};
		String[] edgesVertexA = {"B","C"};
		String[] edgesVertexB = {"G","D"};
		String[] edgesVertexC = {"D"};
		String[] edgesVertexD = {"E"};
		String[] edgesVertexE = {"F","B"};
		String[] edgesVertexF = {"G"};
		
		ConcurrentHashMap<String,String[]> edgeMap = (ConcurrentHashMap<String, String[]>) new ConcurrentHashMap();
		edgeMap.put("A", edgesVertexA);
		edgeMap.put("B",edgesVertexB);
		edgeMap.put("C",edgesVertexC);
		edgeMap.put("D",edgesVertexD);
		edgeMap.put("E",edgesVertexE);
		edgeMap.put("F",edgesVertexF);
		ConcurrentHashMap<String, Vertex> vertexMap = GraphUtility.getVertexMap(verticeValues);
		graphInstance.createGraph(vertexMap, edgeMap);
		String[] vertices = {"G","F","E","D","B","C","A"};
		List<String> verticesList = Arrays.asList(vertices);
		Assert.assertArrayEquals(vertices, graphInstance.traverseDFS("A").toArray());
	}
	
	@Test
	public void testIsCyclicSimpleSuccess() {
		String[] verticeValues = {"A","B","C"};
		String[] edgesVertexA = {"B"};
		String[] edgesVertexB = {"C"};
		String[] edgesVertexC = {"A"};
		
		ConcurrentHashMap<String,String[]> edgeMap = (ConcurrentHashMap<String, String[]>) new ConcurrentHashMap();
		edgeMap.put("A", edgesVertexA);
		edgeMap.put("B",edgesVertexB);
		edgeMap.put("C",edgesVertexC);
		ConcurrentHashMap<String, Vertex> vertexMap = GraphUtility.getVertexMap(verticeValues);
		graphInstance.createGraph(vertexMap, edgeMap);
		String[] vertices = {"A","B","C"};
		List<String> verticesList = Arrays.asList(vertices);
		Assert.assertTrue(graphInstance.isCyclic());
	}
	
	@Test
	public void testIsCyclicComplexSuccess() {
		String[] verticeValues = {"A","B","C","D","E","F","G"};
		String[] edgesVertexA = {"B"};
		String[] edgesVertexB = {"C"};
		String[] edgesVertexC = {"D"};
		String[] edgesVertexD = {"E","B"};
		String[] edgesVertexE = {"F",};
		String[] edgesVertexF = {"G"};
		String[] edgesVertexG = {"A"};
		
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
		String[] vertices = {"A","B","C","D","E","F","G"};
		List<String> verticesList = Arrays.asList(vertices);
		Assert.assertTrue(graphInstance.isCyclic());
	}
	
	@Test
	public void testIsCyclicComplexLongCycleSuccess() {
		String[] verticeValues = {"A","B","C","D","E","F","G"};
		String[] edgesVertexA = {"B"};
		String[] edgesVertexB = {"C"};
		String[] edgesVertexC = {"D"};
		String[] edgesVertexD = {"E"};
		String[] edgesVertexE = {"F",};
		String[] edgesVertexF = {"G"};
		String[] edgesVertexG = {"A"};
		
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
		String[] vertices = {"A","B","C","D","E","F","G"};
		List<String> verticesList = Arrays.asList(vertices);
		Assert.assertTrue(graphInstance.isCyclic());
	}
}