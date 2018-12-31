package com.nik.test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import com.nik.tutorial.graph.mst.Node;
import com.nik.tutorial.graph.mst.DisjointSets;

public class DisjointSetTest {
	private DisjointSets instance  = new DisjointSets();
	
	//@Test
	public void testmakeSetSuccess() {
		ArrayList<Node> nodeList = instance.makeSet(5);
		Assert.assertTrue((nodeList.stream()
		.filter(node->node.equals(node.getParent())))
		.count()==5);
	}
	
	@Test
	public void testSimpleUnionSuccess() {
		ArrayList<Node> nodeList = instance.makeSet(5);
		instance.union(nodeList.get(0), nodeList.get(1));
		instance.union(nodeList.get(2), nodeList.get(3));

		Assert.assertTrue(nodeList.get(0).getRank() == 1);
	}
	
	@Test
	public void testComplexUnionSuccess() {
		ArrayList<Node> nodeList = instance.makeSet(8);
		instance.union(nodeList.get(0), nodeList.get(1));
		instance.union(nodeList.get(2), nodeList.get(3));
		instance.union(nodeList.get(4), nodeList.get(5));
		instance.union(nodeList.get(0), nodeList.get(3));
		instance.union(nodeList.get(3), nodeList.get(4));
		instance.union(nodeList.get(6), nodeList.get(7));
		instance.union(nodeList.get(2), nodeList.get(6));
		
		Assert.assertTrue(nodeList.get(7).getParent().equals(nodeList.get(0)));
		Assert.assertTrue(nodeList.get(2).getParent().equals(nodeList.get(0)));
		Assert.assertTrue(nodeList.get(0).getRank()==2);
	}
}