package com.nik.test;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;
import com.nik.tutorial.graph.mst.KruskalsAlgo;
import com.nik.tutorial.graph.mst.Node;
import com.nik.tutorial.graph.mst.Edge;

public class KruskalsAlgoTest {
	private  KruskalsAlgo instance = new KruskalsAlgo();

	@Test
	public void testKruskalsAlgoSimple() {
		
		Node v1 = new Node(1);
		Node v2 = new Node(2);
		Node v3 = new Node(3);
		Node v4 = new Node(4);
		Node v5 = new Node(5);
		Node v6 = new Node(6);
		
		Edge e1 = new Edge(v1, v2, Double.valueOf("3"));
		Edge e2 = new Edge(v2, v3, Double.valueOf("1"));
		Edge e3 = new Edge(v3, v4, Double.valueOf("1"));
		Edge e4 = new Edge(v1, v4,Double.valueOf("1"));
		Edge e5 = new Edge(v3, v6,Double.valueOf("4"));
		Edge e6 = new Edge(v5, v6,Double.valueOf("2"));
		Edge e7 = new Edge(v4, v5,Double.valueOf("6"));
		Edge e8 = new Edge(v2, v4,Double.valueOf("3"));
		Edge e9 = new Edge(v3, v5,Double.valueOf("5"));
		
		ArrayList<Edge> graphEdges = new ArrayList();
		graphEdges.add(e1);
		graphEdges.add(e2);
		graphEdges.add(e3);
		graphEdges.add(e4);
		graphEdges.add(e5);
		graphEdges.add(e6);
		graphEdges.add(e7);
		graphEdges.add(e8);
		graphEdges.add(e9);
	
		instance.createGraph(graphEdges);
		
		HashSet<Edge> edgeSet = instance.findMST();
		HashSet<Edge> expectedEdgeSet = new HashSet();
		expectedEdgeSet.add(e3);
		expectedEdgeSet.add(e2);
		expectedEdgeSet.add(e4);
		expectedEdgeSet.add(e5);
		expectedEdgeSet.add(e6);

		
		assertTrue(edgeSet.equals(expectedEdgeSet));
	}
}
