package com.nik.test;

import org.junit.Assert;
import org.junit.Test;
import com.nik.tutorial.dp.CuttingRod;

public class CuttingRodTest {
	private CuttingRod instance = new CuttingRod();
		
	@Test
	public void testSimpleCuttingRodCaseSuccess() {
		int [] price = {1,5,8,9};
		int maxPrice = instance.findTheOptimalCut(price, 1);
		Assert.assertTrue(maxPrice==10);
	}
}