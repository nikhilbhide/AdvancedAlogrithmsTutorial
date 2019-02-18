package com.nik.tutorial.binarytree;

/**
 * Represents node entity of the binary tree
 * A node may have left child and a right child
 * 
 * @author nikhil.bhide
 *
 */
public class Node {
	private int value;
	private Node leftChild;
	private Node rightChild;

	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}

	public Node getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}
	public Node getRightChild() {
		return rightChild;
	}
	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}	
}