package com.nik.tutorial.sorting;

/**
 * Class that provides implementation of insertion sort.
 * In insertion sort, element is compared with each of the element on the left, resulting in sorted array.
 * 
 * @author nikhil.bhide
 *
 */
public class InsertionSort {
	public int[] performInsertion(int[] num) {
		for(int i=num.length-1;i>0;i--) {
			for(int j=i-1;j>=0;j--) {
				if(num[j]>num[i]) {
					int temp = num[i];
					num[i] = num[j];
					num[j] = temp;
				}
			}
		}
		return num;
	}
}