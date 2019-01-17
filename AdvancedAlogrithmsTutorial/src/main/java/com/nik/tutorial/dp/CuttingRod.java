package com.nik.tutorial.dp;

/**
 * Given a rod of length n inches and an array of prices that contains prices of all pieces of size smaller than n. 
 * Determine the maximum value obtainable by cutting up the rod and selling the pieces. 
 * 
 * @author nikhil.bhide
 */
public class CuttingRod {

	/**
	 * Provides the implementation using dynamic programming
	 * 
	 * @param price The array containing prices
	 * @param size The size of the cut
	 */
	public int findTheOptimalCut(int[] price, int size) {
		int  maxPrice= Integer.MIN_VALUE;

		if(size<=0) {
			//base case
			return 0;
		}

		for(int counter=0;counter<=size-1;counter++) {
			maxPrice = Math.max(maxPrice, price[counter]+findTheOptimalCut(price,size-counter-1));
		}

		return maxPrice;
	}
}