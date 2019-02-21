package com.nik.tutorial.binarytree;


public class SegmentTree {
	private int[] segmentTree;
	private int [] array;

	public int[] getSegmentTree() {
		return segmentTree;
	}

	public void setSegmentTree(int[] segmentTree) {
		this.segmentTree = segmentTree;
	}

	public SegmentTree(int [] inputArray) {
		segmentTree = new int[2*inputArray.length-1];
		constructSegmentTree(inputArray,0,inputArray.length-1,0);
		array = inputArray;
	}

	/**
	 * Performs update operation by updating value of a particular index of the original array
	 * @param index The index of the original array, the value at index is to be updated
	 * @param newValue The new value at the index
	 */
	public void updateIndex(int index, int newValue) {
		array[index] = newValue;
		updateIndex(index,0,array.length-1,0);
	}

	/**
	 * Performs update operation by updating value of a particular index of the original array
	 * 
	 * @param index The index of the original array, the value at index is to be updated
	 * @param low The lowest end of the range in consideration
	 * @param high The highest end of the range in consideration
	 * @param pos The current position of segment tree
	 */
	private void updateIndex(int index, int low, int high, int pos) {
		if(high==low) {
			segmentTree[pos] = array[low];
		}
		else {
			int mid = findMid(high, low);
			if(low<=index && mid>=index) {
				updateIndex(index,low,mid,2*pos+1);
			}
			else {
				updateIndex(index,mid+1,high,2*pos+2);
			}
			segmentTree[pos] = Math.min(segmentTree[2*pos+1], segmentTree[2*pos+2]);
		}
	}
	
	/** Finds the minimum value for the given range
	 * @param qlow The low value of the range of the query
	 * @param qhigh The high value of the range of the query
	 * @return The minimum value of the given range
	 */
	public int queryMinimum(int qlow, int qhigh) {
		return queryMinimum(qlow, qhigh,0,array.length,0);
	}


	/** Finds the minimum value for the given range
	 * @param qlow The low value of the range of the query
	 * @param qhigh The high value of the range of the query
	 * @param low The lowest end of the range in consideration
	 * @param high The highest value of the range in consideration
	 * @param pos The index element of the segment tree
	 * 
	 * @return The minimum value of the given range
	 */
	private int queryMinimum(int qlow, int qhigh, int low, int high, int pos) {
		if(qlow<=low && qhigh>=high) {
			//total overlap case
			return segmentTree[pos];
		}
		else if(qlow>high || qhigh <low) {
			//no overlap case
			return Integer.MAX_VALUE;
		}
		else {
			//partial overlap
			int mid = findMid(high, low);
			return Math.min(queryMinimum(qlow,qhigh,low,mid,2*pos+1),queryMinimum(qlow,qhigh,mid+1,high,2*pos+2));
		}
	}

	/**
	 * Constructs the segement tree from the given array
	 * 
	 * @param inputArray The input array from which ST is to be constructed
	 * @param low The lowest end of the range in consideration
	 * @param high The highest value of the range in consideration
	 * @param pos The index element of the segment tree
	 */
	public void constructSegmentTree(int[] inputArray, int low, int high, int pos) {
		if(low==high) {
			segmentTree[pos] = inputArray[low];
			return;
		}
		else if(low<=high){
			int mid = findMid(high,low);
			//construct left sub tree
			constructSegmentTree(inputArray,low,mid,2*pos+1);
			//construct right sub tree
			constructSegmentTree(inputArray,mid+1,high,2*pos+2);
			//find out minimum of left and right sub tree and populate the root value of the tree in question
			segmentTree[pos] = Math.min(segmentTree[2*pos+1], segmentTree[2*pos+2]);
		}
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
