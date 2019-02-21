package com.nik.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.nik.tutorial.binarytree.BinaryTreeSpiralTraversal;
import com.nik.tutorial.binarytree.Node;

public class BinaryTreeSpiralTraversalTest {
	private BinaryTreeSpiralTraversal instance;

	@Before
	public void init() {
		instance = new BinaryTreeSpiralTraversal();
	}

	@After
	public void tearDown() {
		instance = null;
	}

	@Test
	public void testBinaryTreeSpiralTraversal() throws Exception {
		Node leftChild = new Node();
		leftChild.setValue(10);
		Node rightChild = new Node();
		rightChild.setValue(20);
		
		Node leftChild1 = new Node();
		leftChild1.setValue(30);
		Node rightChild1 = new Node();
		rightChild1.setValue(40);
		leftChild.setLeftChild(leftChild1);
		leftChild.setRightChild(rightChild1);

		Node leftChild2 = new Node();
		leftChild2.setValue(50);
		Node rightChild2 = new Node();
		rightChild2.setValue(60);
		rightChild1.setLeftChild(leftChild2);
		rightChild1.setRightChild(rightChild2);

		Node leftChild3 = new Node();
		leftChild3.setValue(70);
		Node rightChild3 = new Node();
		rightChild3.setValue(80);
		rightChild.setLeftChild(leftChild3);
		rightChild.setRightChild(rightChild3);

		Node leftChild4 = new Node();
		leftChild4.setValue(90);
		Node rightChild4 = new Node();
		rightChild4.setValue(100);
		rightChild3.setLeftChild(leftChild4);
		rightChild3.setRightChild(rightChild4);

		instance.getRootNode().setValue(5);
		instance.getRootNode().setLeftChild(leftChild);
		instance.getRootNode().setRightChild(rightChild);
		
		ArrayList<Integer> result = instance.getSpiralTraversalOrder();
		int[] array = {5,20,10,30,40,70,80,100,90,60,50};
		List<Integer> expectedList = Arrays.stream( array ).boxed().collect( Collectors.toList());
		Assert.assertEquals(result, expectedList);
	}
}