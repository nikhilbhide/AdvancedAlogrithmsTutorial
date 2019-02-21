package com.nik.tutorial.binarytree;

import java.util.ArrayList;
import java.util.Stack;

public class BinaryTreeSpiralTraversal {
	private Node rootNode;
	private Stack<Node> stack1;
	private Stack<Node> stack2;

	public BinaryTreeSpiralTraversal() {
		stack1 = new Stack <Node>();
		stack2 = new Stack <Node>();
		rootNode = new Node();
	}

	public Node getRootNode() {
		return rootNode;
	}

	public void setRootNode(Node rootNode) {
		this.rootNode = rootNode;
	}

	public ArrayList<Integer> getSpiralTraversalOrder() throws Exception {
		if(rootNode==null) {
			throw new Exception("Tree is empty");
		} else {
			ArrayList<Integer> result = new ArrayList();
			stack1.push(rootNode);
			do {
				while(!stack1.isEmpty()) {
					Node currentNode = stack1.pop();
					if(currentNode.getLeftChild()!=null) {
						stack2.push(currentNode.getLeftChild());
					}
					if(currentNode.getRightChild()!=null) {
						stack2.push(currentNode.getRightChild());
					}
					result.add(currentNode.getValue());
				}
				while(!stack2.isEmpty()) {
					Node currentNode = stack2.pop();
					if(currentNode.getRightChild()!=null) {
						stack1.push(currentNode.getRightChild());
					}
					if(currentNode.getLeftChild()!=null) {
						stack1.push(currentNode.getLeftChild());
					}
					result.add(currentNode.getValue());
				}
			} while(!stack1.isEmpty() || !stack2.isEmpty());
			return result;
		}
	}
}