package com.nik.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.nik.tutorial.binarytree.BTFindLowestCommonAncestor;
import com.nik.tutorial.binarytree.Node;

public class TestLowestCommonAncestorBinaryTree {
	private BTFindLowestCommonAncestor instance;
	
	@Before
	public void init() {
		instance = new BTFindLowestCommonAncestor();
	}
	
	@After
	public void tearDown() {
		instance = null;
	}
	
	//@Test
	public void testLowestCommonAncestorSimpleSuccess() {
		Node rootNode = new Node();
		rootNode.setValue(10);
		instance.setRoot(rootNode);
		
		Node rightChild = new Node();
		rightChild.setValue(5);
		
		Node leftChild = new Node();
		leftChild.setValue(15);
		
		rootNode.setLeftChild(leftChild);
		rootNode.setRightChild(rightChild);
		
		Assert.assertEquals(instance.findCommonAncestor(5,15),rootNode);
	}	
	
	@Test
	public void testLowestCommonAncestorTwoLevelBTSuccess() {
		Node rootNode = new Node();
		rootNode.setValue(10);
		instance.setRoot(rootNode);
		
		Node leftChild = new Node();
		leftChild.setValue(15);
		Node leftChild1 = new Node();
		leftChild1.setValue(3);
		Node rightChild1 = new Node();
		rightChild1.setValue(13);
		leftChild.setLeftChild(leftChild1);
		leftChild.setRightChild(rightChild1);

		Node rightChild = new Node();
		rightChild.setValue(5);
		
		rootNode.setLeftChild(leftChild);
		rootNode.setRightChild(rightChild);
		
		Assert.assertEquals(instance.findCommonAncestor(3,13),leftChild);
	}	
	
	@Test
	public void testLowestCommonAncestorComplexSSuccess() {
		Node rootNode = new Node();
		rootNode.setValue(10);
		instance.setRoot(rootNode);
		
		Node leftChild = new Node();
		leftChild.setValue(15);
		Node leftChild1 = new Node();
		leftChild1.setValue(3);
		Node rightChild1 = new Node();
		rightChild1.setValue(13);
		leftChild.setLeftChild(leftChild1);
		leftChild.setRightChild(rightChild1);

		Node rightChild = new Node();
		rightChild.setValue(5);
		
		rootNode.setLeftChild(leftChild);
		rootNode.setRightChild(rightChild);
		
		Assert.assertEquals(instance.findCommonAncestor(3,13),leftChild);
	}	
}