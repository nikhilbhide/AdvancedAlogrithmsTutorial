package com.nik.test;

import com.nik.tutorial.sorting.MergeSort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

public class MergeSortTest {
	private MergeSort instance = new MergeSort();
	
	@Test
	public void testMergeSortSimplePositiveNumbersSuccess() {
		int[] num = {10,40,50,2,5,9,20};
		int[] sortedArray = instance.mergeSort(num);
		int[]  expectedSortedArray = {2,5,9,10,20,40,50};
		Assert.assertArrayEquals(expectedSortedArray, sortedArray);
	}
	
	@Test
	public void testMergeSortSimpleNegativeNumbersSuccess() {
		int[] num = {10,-40,50,-2,5,-9,20};
		int[] sortedArray = instance.mergeSort(num);
		int[]  expectedSortedArray = {-40,-9,-2,5,10,20,50};
		Assert.assertArrayEquals(expectedSortedArray, sortedArray);
	}
	
	@Test
	public void testMergeSortComplexPositiveNumbersSuccess() {
		int[] num = {10,0,90,4,5,300,5000,200,90,100,120,150,169};
		List<Integer> expectedSortedArray  = Arrays.stream( num ).boxed().collect( Collectors.toList());
		Collections.sort(expectedSortedArray);
		Assert.assertArrayEquals(expectedSortedArray.stream().mapToInt(i->i).toArray(), instance.mergeSort(num));
	}
}