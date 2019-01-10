package com.nik.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.nik.tutorial.sorting.InsertionSort;

public class InsertionSortTest {
	private InsertionSort instance;

	@Before
	public void setUp() {
		instance = new InsertionSort();
	}
	
	@After
	public void tearDown() {
		instance = null;
	}
	
	@Test
	public void testSimpleNumbersSortingSuccess() {
		int[] num = {10,40,50,2,5,9,20};
		int[] sortedArray = instance.performInsertion(num);
		int[]  expectedSortedArray = {2,5,9,10,20,40,50};
		Assert.assertArrayEquals(expectedSortedArray, sortedArray);
	}
	
	@Test
	public void testMergeSortSimpleNegativeNumbersSuccess() {
		int[] num = {10,-40,50,-2,5,-9,20};
		int[] sortedArray = instance.performInsertion(num);
		int[]  expectedSortedArray = {-40,-9,-2,5,10,20,50};
		Assert.assertArrayEquals(expectedSortedArray, sortedArray);
	}
	
	@Test
	public void testMergeSortComplexPositiveNumbersSuccess() {
		int[] num = {10,0,90,4,5,300,5000,200,90,100,120,150,169};
		List<Integer> expectedSortedArray  = Arrays.stream( num ).boxed().collect( Collectors.toList());
		Collections.sort(expectedSortedArray);
		Assert.assertArrayEquals(expectedSortedArray.stream().mapToInt(i->i).toArray(), instance.performInsertion(num));
	}
}