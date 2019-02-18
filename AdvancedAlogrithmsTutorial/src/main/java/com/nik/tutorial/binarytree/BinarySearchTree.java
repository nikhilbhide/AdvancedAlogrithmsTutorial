package com.nik.tutorial.binarytree;

/**
 * This class represents binary search tree.
 * Binary Search Tree, is a node-based binary tree data structure which has the following properties:
 * 1. The left subtree of a node contains only nodes with keys lesser than the node’s key.
 * 2. The right subtree of a node contains only nodes with keys greater than the node’s key.
 * 3. The left and right subtree each must also be a binary search tree.
 * 
 * @author nikhil.bhide
 *
 */

public class BinarySearchTree {
	private Node rootNode;
	
	public BinarySearchTree() {
		rootNode = new Node();
	}
	
	public Node getRootNode() {
		return rootNode;
	}

	public void setRootNode(Node rootNode) {
		this.rootNode = rootNode;
	}

	/**
	 * Checks whether current BST is a valid BST.
	 * It evaluates the tree based on the properties of the BST.
	 * 
	 * @return <code> true </code> if a given binary tree is BST otherwise false
	 */
	public boolean isBST() {
		return isBST(rootNode,Integer.MIN_VALUE,Integer.MAX_VALUE);
	}
	
	/**
	 * Checks whether current BST is a valid BST.

	 * @param currentNode The node in traversal
	 * @param min The minimum value of the range
	 * @param max The maximum value of the range 
	 * 
	 * @return <code> true </code> if a given binary tree is BST otherwise false
	 */
	private boolean isBST(Node currentNode, int min, int max) {
		if(!(currentNode.getValue()>=min && currentNode.getValue()<=max)) {
			return false;
		}
		else {
			if(currentNode.getLeftChild()!=null && currentNode.getRightChild()!=null) {
				boolean isLeftSubTreeBST = isBST(currentNode.getLeftChild(),min,currentNode.getValue());
				boolean isRightSubTreeBST = isBST(currentNode.getRightChild(),currentNode.getValue(),max);
				return isLeftSubTreeBST && isRightSubTreeBST;
			}
			else if(currentNode.getLeftChild()!=null) {
				return isBST(currentNode.getLeftChild(),min,currentNode.getValue());
			}
			else if(currentNode.getLeftChild()!=null) {
				return isBST(currentNode.getRightChild(),currentNode.getValue(),max);
			}
			else {
				return true;
			}
		}
	}
}