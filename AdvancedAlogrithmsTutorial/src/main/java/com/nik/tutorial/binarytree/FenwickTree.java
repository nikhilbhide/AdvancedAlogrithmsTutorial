package com.nik.tutorial.binarytree;

/**
 * Fenwick tree is the most efficient data structure (in terms of time and space complexity) to perform operations that include range queries.
 * Range queries could be operations such as sum, min, max etc.
 * The problem statement is as follows -
 * 
 * We have an array arr[0 . . . n-1]. We would like to
 * 		1 Compute the sum of the first i-th elements.
 *		2 Modify the value of a specified element of the array arr[i] = x where 0 <= i <= n-1.
 *
 * Fenwick tree 
 * @author nikhil.bhide
 *
 */
public class FenwickTree {
	private int[] fenwickTree;
	private int [] array;

	public int[] getFenwickTree() {
		return fenwickTree;
	}

	public void setFenwickTree(int[] fenwickTree) {
		this.fenwickTree = fenwickTree;
	}

	public FenwickTree(int [] inputArray) {
		fenwickTree = new int[inputArray.length+1];
		constructFenwickTree(inputArray);
		array = inputArray;
	}

	/**
	 * Performs update operation by updating value of a particular index of the original array
	 * @param index The index of the original array, the value at index is to be updated
	 * @param newValue The new value at the index
	 */
	public void updateIndex(int index, int newValue) {
		int diff = newValue - array[index];
		int upperBound = array.length+1;

		array[index] = newValue;
		int next = index+1;

		while(next<upperBound) {
			fenwickTree[next] = fenwickTree[next] + diff;
			next = getNext(next);
		}
	}

	/** Returns sum of arr[0..index]. This function assumes that the array is precomputed
	 *  and partial sums of array elements are stored in Fenix tree  
	 *  
	 *  @return The sum of the values in range of 0..index from the input array
	 **/
	public int getSum(int index) {
		int lowerBound = 0;
		int pos = index + 1;
		int result = 0;

		while(pos>lowerBound) {
			result = fenwickTree[pos] + result;
			pos = getParent(pos);
		}   

		return result;
	}

	/**
	 * Constructs the Fenwick tree from the given array
	 * @param inputArray The input array from which ST is to be constructed
	 */
	public void constructFenwickTree(int[] inputArray) {
		int upperBound = inputArray.length+1;
		for(int counter=0; counter<inputArray.length; counter++) {
			int next = counter+1;
			while(next<upperBound) {
				fenwickTree[next] = fenwickTree[next] + inputArray[counter];
				next = getNext(next);				
			}
		}		
	}

	/**
	 * Computes the parent of the current node in the consideration
	 * The formula to compute the next node is as follows 
	 * Calculate the 2's complement of the index of the segment tree. The index of the segment tree = position in the original array + 1
	 * And the result with originial number.  
	 * @param next
	 * @return
	 */
	private int getParent(int next) {
		return next - (next & -next);
	}

	private int getNext(int next) {
		return next + (next & -next);
	}

	/**
	 * Finds out mid of the provided range
	 * @param high The highest value of the range
	 * @param low The lowest value of the range
	 * 
	 * @return The mid of the range
	 */
	private int findMid(int high, int low) {
		return (high + low)/2;
	}
}