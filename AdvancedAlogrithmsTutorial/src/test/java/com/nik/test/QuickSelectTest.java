package com.nik.test;

import org.junit.Assert;
import org.junit.Test;
import com.nik.tutorial.sorting.QuickSelect;

public class QuickSelectTest {
private QuickSelect instance = new QuickSelect();
	
	@Test
	public void testQuickSelectMidPositionSuccess() {
		int[] num = {10,40,50,2,5,9,20};
		int kthElement = instance.quickSelect(num,2);
		Assert.assertTrue(kthElement==9);
	}	
	
	@Test
	public void testQuickSelectExtremeRightPositionSuccess() {
		int[] num = {10,40,50,2,5,9,20};
		int kthElement = instance.quickSelect(num,6);
		Assert.assertTrue(kthElement==50);
	}	
	
	@Test
	public void testQuickSelectExtremeLeftPositionSuccess() {
		int[] num = {10,40,50,2,5,9,20};
		int kthElement = instance.quickSelect(num,0);
		Assert.assertTrue(kthElement==2);
	}	
}