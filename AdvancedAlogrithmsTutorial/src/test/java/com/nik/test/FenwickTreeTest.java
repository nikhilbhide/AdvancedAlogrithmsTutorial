package com.nik.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import com.nik.tutorial.binarytree.FenwickTree;

public class FenwickTreeTest {
	private FenwickTree instance;

	@After
	public void tearDown() {
		instance = null;
	}

	@Test
	public void testSimpleSumFTConstructionSuccess() {
		int [] array = {1,2,3};
		instance = new FenwickTree(array);
		Assert.assertEquals(instance.getFenwickTree().length,4);
		Assert.assertEquals(1,instance.getFenwickTree()[1]);
		Assert.assertEquals(3,instance.getFenwickTree()[2]);
		Assert.assertEquals(3,instance.getFenwickTree()[3]);
	}
	
	@Test
	public void testComplexSumWidNegativeNumbersFTConstructionSuccess() {
		int [] array = {3,2,-1,6,5,4,-3,3,7,2,3};
		instance = new FenwickTree(array);
		Assert.assertEquals(3,instance.getFenwickTree()[1]);
		Assert.assertEquals(5,instance.getFenwickTree()[2]);
		Assert.assertEquals(10,instance.getFenwickTree()[4]);
		Assert.assertEquals(19,instance.getFenwickTree()[8]);
		Assert.assertEquals(-1,instance.getFenwickTree()[3]);
		Assert.assertEquals(5,instance.getFenwickTree()[5]);
		Assert.assertEquals(-3,instance.getFenwickTree()[7]);
		Assert.assertEquals(3,instance.getFenwickTree()[11]);
	}	
	
	@Test
	public void testComplexSumWidNegativeNumbersFTUpdateSuccess() {
		int [] array = {3,2,-1,6,5,4,-3,3,7,2,3};
		instance = new FenwickTree(array);
		instance.updateIndex(3, 9);
		Assert.assertEquals(3,instance.getFenwickTree()[1]);
		Assert.assertEquals(5,instance.getFenwickTree()[2]);
		Assert.assertEquals(13,instance.getFenwickTree()[4]);
		Assert.assertEquals(22,instance.getFenwickTree()[8]);
	}	
	
	@Test
	public void testComplexSumWidNegativeNumbersFTGetSumSuccess() {
		int [] array = {3,2,-1,6,5,4,-3,3,7,2,3};
		instance = new FenwickTree(array);
		Assert.assertEquals(28,instance.getSum(9));
	}	
}