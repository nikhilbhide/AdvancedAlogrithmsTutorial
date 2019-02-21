package com.nik.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import com.nik.tutorial.binarytree.SegmentTree;

public class SegmentTreeTest {
	private SegmentTree instance;

	@After
	public void tearDown() {
		instance = null;
	}

	@Test
	public void testSimpleMinSTConstructionSuccess() {
		int [] array = {1,2,3};
		instance = new SegmentTree(array);
		Assert.assertEquals(instance.getSegmentTree().length,5);
		Assert.assertEquals(1,instance.getSegmentTree()[1]);
		Assert.assertEquals(1,instance.getSegmentTree()[0]);
		Assert.assertEquals(3,instance.getSegmentTree()[2]);
		Assert.assertEquals(1,instance.getSegmentTree()[3]);
		Assert.assertEquals(2,instance.getSegmentTree()[4]);
	}
	
	@Test
	public void testSimpleMinSTQuerySuccess() {
		int [] array = {1,2,3};
		instance = new SegmentTree(array);
		Assert.assertEquals(2,instance.queryMinimum(1, 2));
	}
	
	@Test
	public void testSimpleUpdateSuccess() {
		int [] array = {1,2,3};
		instance = new SegmentTree(array);
		instance.updateIndex(1,0);
		Assert.assertEquals(0,instance.getSegmentTree()[0]);
	}

	@Test
	public void testSimpleUpdateLeastValueSuccess() {
		int [] array = {1,2,3};
		instance = new SegmentTree(array);
		instance.updateIndex(0,10);
		Assert.assertEquals(2,instance.getSegmentTree()[0]);
		Assert.assertEquals(2,instance.queryMinimum(1, 2));

	}

	//@Test
	public void testComplexMinSTConstruction() {
		int [] array = {1,2,3,5,6,7,8};
		instance = new SegmentTree(array);
		Assert.assertEquals(instance.getSegmentTree().length,5);
		Assert.assertEquals(1,instance.getSegmentTree()[1]);
		Assert.assertEquals(1,instance.getSegmentTree()[0]);
		Assert.assertEquals(3,instance.getSegmentTree()[2]);
		Assert.assertEquals(1,instance.getSegmentTree()[3]);
		Assert.assertEquals(2,instance.getSegmentTree()[4]);
	}
}