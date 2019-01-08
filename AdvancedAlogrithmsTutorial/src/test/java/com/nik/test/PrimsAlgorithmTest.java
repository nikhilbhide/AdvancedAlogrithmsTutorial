package com.nik.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import com.nik.tutorial.graph.mst.Edge;
import com.nik.tutorial.graph.mst.Node;
import com.nik.tutorial.graph.mst.PrimsAlgorithm;


public class PrimsAlgorithmTest {

	private PrimsAlgorithm instance  = new PrimsAlgorithm();

	@Test
	public void testShortestPathSimplgeGraph() {
		Node v1 = new Node("A");
		Node v2 = new Node("B");
		Node v3 = new Node("C");
		Node v4 = new Node("D");
		instance.getVertexMap().put("A", v1);
		instance.getVertexMap().put("B", v2);
		instance.getVertexMap().put("C", v3);
		instance.getVertexMap().put("D", v4);

		Edge e1 = new Edge(v1, v2, 5);
		Edge e2 = new Edge(v1, v3, 2);
		Edge e3 = new Edge(v2, v4, 1);
		Edge e4 = new Edge(v3, v4, 3);

		Edge e5 = new Edge(v2, v1, 5);
		Edge e6 = new Edge(v3, v1, 2);
		Edge e7 = new Edge(v4, v2, 1);
		Edge e8 = new Edge(v4, v3, 3);
		
		List<Edge> edgesA = new ArrayList();
		edgesA.add(e1);
		edgesA.add(e2);
		v1.setNeighbours(edgesA);

		List<Edge> edgesB = new ArrayList();
		edgesB.add(e3);
		edgesB.add(e5);

		v2.setNeighbours(edgesB);

		List<Edge> edgesC = new ArrayList();
		edgesC.add(e4);
		edgesC.add(e6);
		v3.setNeighbours(edgesC);
		
		List<Edge> edgesD = new ArrayList();
		edgesD.add(e7);
		edgesD.add(e8);
		v4.setNeighbours(edgesD);

		List<Edge> mst = new ArrayList<Edge>();
		mst.add(e2);
		mst.add(e4);
		mst.add(e7);
		Assert.assertTrue(Arrays.deepEquals(mst.toArray(), instance.createSpanningTree(v1).toArray()));
	}
}