package com.nik.tutorial.sorting;

/**
 * Provides implementation of Merge Sort 
 * 
 * @author nikhil.bhide
 *
 */

public class MergeSort {
	/**
	 * Partitions array into subarrays based on the mid position.
	 * Merge sort is based on divide and conquer strategy and partitioning happens till the point at which only one elements remains
	 * It executes the divide phase of divide and conquer algorithm
	 * 
	 * @param a The array to be sorted
	 * @param low The start index from which array to be considered
	 * @param high The end index at which array ends
	 * 
	 * @return sorted array
	 */
	public int[] partition(int[] a, int low, int high) {
		if(low!=high) {
			int mid = (low+high)/2;
			int left[] = partition(a,low,mid);
			int right[] = partition(a,mid+1,high);
			return merge(left,right);
		}	
		else {
			int [] tempArray = {a[low]};
			return tempArray;
		}
	}

	/**
	 * It executes the conquer phase of the algorithm in which it merges the left and right subarrays
	 * 
	 * @param left The left subarray to be sorted
	 * @param right The right subarray to be sorted 
	 * 
	 * @return Merged, sorted array out of left and right subarrays
	 */
	public int[] merge(int[] left, int [] right) {
		int i = 0;
		int j = 0;
		int sizeOfTempArray = right.length+left.length;
		int [] result = new int[sizeOfTempArray];
		int counter = 0;

		//compare left and right arrays and copy elements as per the ascending order
		while(i<left.length && j<right.length) {
			if(left[i]<=right[j]) {
				result[counter] = left[i];
				i++;
			}
			else {
				result[counter] = right[j];
				j++;
			}
			counter++;
		}

		//copy rest of the array from left array
		while(i<=left.length-1) {
			result[counter] = left[i];
			i++;
			counter++;
		}

		//copy rest of the array from right array
		while(j<=right.length-1) {
			result[counter] = right[j];
			counter++;
			j++;
		}

		return result;
	}

	public int[] mergeSort(int[] arrayToSort) {
		return partition(arrayToSort, 0, arrayToSort.length-1);
	}
}