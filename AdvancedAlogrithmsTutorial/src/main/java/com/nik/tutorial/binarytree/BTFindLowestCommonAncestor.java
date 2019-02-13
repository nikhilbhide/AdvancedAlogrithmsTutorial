package com.nik.tutorial.binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class BTFindLowestCommonAncestor {
	private Node root;

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public Node findCommonAncestor(int item1, int item2) {
		Queue queue = new LinkedList<Node>();
		queue.add(root.getLeftChild());
		Node exploredLeftNode = exploreSubtree(item1,item2,root,queue);
		
		queue.clear();
		queue.add(root.getRightChild());
		Node exploredRightChild = exploreSubtree(item1,item2,root,queue);
		
		if(exploredLeftNode==null && exploredRightChild!=null) {
			return exploredLeftNode;
		}
		else if(exploredRightChild==null && exploredLeftNode!=null) {
			return exploredRightChild;
		}
		else if(exploredLeftNode!=null && exploredRightChild!=null) {
			return root;
		}
		else {
			throw new RuntimeException("Invalid input");
		}
	}

	/**
	 * Explores the subtree to find whether any of the items item1 or item2 are located.
	 * When a particular item is found, the probe is stopped and corresponding node object is returned.
	 * 
	 * @param item1 The first item of the common ancestor search
	 * @param item2 The second item of the common ancestor search
	 * @param node 
	 */
	private Node exploreSubtree(int item1, int item2,  Node parentNode, Queue<Node> queue) {
		if(!queue.isEmpty()) {
			Node currentNode = queue.remove();
			if(currentNode.getValue()==item1 || currentNode.getValue()==item2) {
				return parentNode;
			}
			if (currentNode.getLeftChild()!=null) {
				queue.add(currentNode.getLeftChild());
			}
			if(currentNode.getRightChild()!=null) {
				queue.add(currentNode.getRightChild());
			}
			
			return exploreSubtree(item1, item2, currentNode, queue);
		}
		else {
			return null;
		}
	}
}