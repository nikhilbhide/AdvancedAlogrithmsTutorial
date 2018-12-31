package com.nik.tutorial.graph.mst;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

/**
 * This class finds the disjoint sets of a graph.
 * It provides the implementation of makeSet, findSet and union operations.
 * Each vertex is represented by a Node class {@link Node}
 * 
 * @author nikhil.bhide
 *
 */
public class DisjointSets {
	
	//A map of node data and representative node name
	private ConcurrentHashMap<String,String> disjointSetsByNodeMap = new ConcurrentHashMap();

	//A map of node name of representative and node list
	private ConcurrentHashMap<String,HashSet<Node>> disjointSetsByRepresentativeMap = new ConcurrentHashMap();
	
	/**
	 * Creates a new disjoint set with given number of vertices.
	 * Node data is set to a default value which is the sequence.
	 * 
	 * @param numVertices The number of vertices
	 * 
	 * @return List of nodes and each node is a disjoint set on its own
	 */
	public ArrayList<Node> makeSet(int numVertices) {
		ArrayList<Node> nodeList = new ArrayList();
		
		IntStream.rangeClosed(1, numVertices).forEach(index-> {
			Node node = new Node(index);
			nodeList.add(node);
			disjointSetsByNodeMap.put(String.valueOf(index), String.valueOf(index));
			HashSet<Node> childNodes = new HashSet();
			childNodes.add(node);
			disjointSetsByRepresentativeMap.put(String.valueOf(index), childNodes);
		});
		
		return nodeList;
	}

	/**
	 * Unite two disparate disjoint sets together.
	 * If two nodes are part of same disjoint sets then it does not do anything.
	 * Otherwise, it merges lower rank disjoint set into greater rank disjoint set.
	 * In case both the nodes have same rank, then rank of anyone's node is incremented by 1 and that particular node is promoted as representative of a disjoint node.
	 * 
	 * @param node1 The node to be merged with second node
	 * @param node2 The node to be merged with first node
	 */
	public void union(Node node1, Node node2) {
		Node repNode1 = findSet(node1);
		Node repNode2 = findSet(node2);
		
		if(repNode1.equals(repNode2)) {
			return;
		}
		else if (repNode1.getRank()==repNode2.getRank()){
			repNode1.setRank(repNode1.getRank()+1);
			repNode2.setRank(0);
			adjustRemainingNodesOfFormerRepresentative(repNode2, repNode1);
		}
		else if (repNode1.getRank()>repNode2.getRank()) {
			repNode2.setRank(0);
			repNode2.setParent(node1);
			adjustRemainingNodesOfFormerRepresentative(repNode2, repNode1);
		}
		else {
			repNode1.setRank(0);
			repNode1.setParent(repNode1);
			adjustRemainingNodesOfFormerRepresentative(repNode1, repNode2);
		}
	}

	/**
	 * Adjusts the representative of nodes in the second disjoint set when two disjoint sets are merged.
	 * Its the implementation of path compression
	 * 
	 * @param formerRepNode The node whose children require to point to new parent
 	 * @param newRepNode The new parent node

	 */
	private void adjustRemainingNodesOfFormerRepresentative(Node formerRepNode, Node newRepNode) {
		HashSet<Node> nodeSet = disjointSetsByRepresentativeMap.get(String.valueOf(formerRepNode.getData()));
		
		disjointSetsByRepresentativeMap.get(String.valueOf(newRepNode.getData())).add(formerRepNode);
		
		for(Node node:nodeSet) {
			node.setParent(newRepNode);
			disjointSetsByNodeMap.put(String.valueOf(String.valueOf(node.getData())),String.valueOf(newRepNode.getData()));
			disjointSetsByRepresentativeMap.get(String.valueOf(newRepNode.getData())).add(node);
		}
		
		disjointSetsByRepresentativeMap.remove(formerRepNode);
	}

	/**
	 * Find the representative of given node
	 * 
	 * @param node The node under traversal
	 * 
	 * @return The parent node of given node by finding out the parent node
	 */
	private Node findSet(Node node) {
		if(node.getParent()==node) {
			return node;
		}
		else 
			return findSet(node.getParent());
	}
}