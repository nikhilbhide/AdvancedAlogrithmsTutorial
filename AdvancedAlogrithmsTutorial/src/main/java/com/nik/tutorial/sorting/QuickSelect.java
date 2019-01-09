package com.nik.tutorial.sorting;

public class QuickSelect {
	private int partition(int [] a, int left, int right) {
		int pivote = left;
		int leftMark = left + 1;
		int rightMark = right;

		while (true) {
			while(a[leftMark]<a[pivote] && leftMark<right) {
				leftMark++;
			}

			while(a[rightMark] > a[pivote] && rightMark>left) {
				rightMark--;
			}

			if(rightMark<=leftMark)
				break;

			int temp = a[leftMark];
			a[leftMark] = a[rightMark];
			a[rightMark] = temp;
		}

		int temp = a[left];
		a[left] = a[rightMark];
		a[rightMark] = temp;

		return rightMark;
	}

	public int quickSelect(int[] a, int k) {
		return quickSelect(a,k,0,a.length-1);
	}

	public int quickSelect(int []a, int k,int left, int right) {
		int split = partition(a,left,right);

		if(split==k)
			return a[split];
		else if(k < split)
			return quickSelect(a,k,left,split-1);
		else
			return quickSelect(a,k,split+1,right);
	}
}