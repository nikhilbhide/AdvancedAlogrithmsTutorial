package com.nik.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.nik.tutorial.binarytree.BinarySearchTree;
import com.nik.tutorial.binarytree.Node;

public class TestBST {
	private BinarySearchTree instance;

	@Before
	public void init() {
		instance = new BinarySearchTree();
	}

	@After
	public void tearDown() {
		instance = null;
	}

	@Test
	public void testIsBSTSimpleSuccess() {
		instance.getRootNode().setValue(100);
		Node leftChild = new Node();
		Node rightChild = new Node();
		instance.getRootNode().setLeftChild(leftChild);
		instance.getRootNode().setRightChild(rightChild);
		instance.getRootNode().getLeftChild().setValue(30);
		instance.getRootNode().getRightChild().setValue(900);
		Assert.assertTrue(instance.isBST());
	}

	@Test
	public void testIsBSTSimpleRightChildSmallerThanRootFailure() {
		instance.getRootNode().setValue(100);
		Node leftChild = new Node();
		Node rightChild = new Node();
		instance.getRootNode().setLeftChild(leftChild);
		instance.getRootNode().setRightChild(rightChild);
		instance.getRootNode().getLeftChild().setValue(30);
		instance.getRootNode().getRightChild().setValue(-900);
		Assert.assertFalse(instance.isBST());
	}

	@Test
	public void testIsBSTLeftChildGreaterThanRootFailure() {
		instance.getRootNode().setValue(100);
		Node leftChild = new Node();
		Node rightChild = new Node();
		instance.getRootNode().setLeftChild(leftChild);
		instance.getRootNode().setRightChild(rightChild);
		instance.getRootNode().getLeftChild().setValue(300);
		instance.getRootNode().getRightChild().setValue(900);
		Assert.assertFalse(instance.isBST());
	}

	@Test
	public void testIsBSTAverageSizeSuccess() {
		instance.getRootNode().setValue(100);
		Node leftChild = new Node();
		Node rightChild = new Node();
		instance.getRootNode().setLeftChild(leftChild);
		instance.getRootNode().setRightChild(rightChild);
		instance.getRootNode().getLeftChild().setValue(30);
		Node leftChild1 = new Node();
		leftChild1.setValue(20);
		Node rightChild1 = new Node();
		rightChild1.setValue(35);
		instance.getRootNode().getLeftChild().setLeftChild(leftChild1);
		instance.getRootNode().getLeftChild().setRightChild(rightChild1);
		Node leftChild2 = new Node();
		Node rightChild2 = new Node();
		leftChild2.setValue(110);
		rightChild2.setValue(1002);
		instance.getRootNode().getRightChild().setValue(900);
		instance.getRootNode().getRightChild().setLeftChild(leftChild2);
		instance.getRootNode().getRightChild().setRightChild(rightChild2);

		Assert.assertTrue(instance.isBST());
	}

	@Test
	public void testIsBSTAverageSizeFailure() {
		instance.getRootNode().setValue(100);
		Node leftChild = new Node();
		Node rightChild = new Node();
		instance.getRootNode().setLeftChild(leftChild);
		instance.getRootNode().setRightChild(rightChild);
		instance.getRootNode().getLeftChild().setValue(30);
		Node leftChild1 = new Node();
		leftChild1.setValue(20);
		Node rightChild1 = new Node();
		rightChild1.setValue(35);
		instance.getRootNode().getLeftChild().setLeftChild(leftChild1);
		instance.getRootNode().getLeftChild().setRightChild(rightChild1);
		Node leftChild2 = new Node();
		Node rightChild2 = new Node();
		leftChild2.setValue(110);
		rightChild2.setValue(102);
		instance.getRootNode().getRightChild().setValue(900);
		instance.getRootNode().getRightChild().setLeftChild(leftChild2);
		instance.getRootNode().getRightChild().setRightChild(rightChild2);
		Assert.assertFalse(instance.isBST());
	}
}