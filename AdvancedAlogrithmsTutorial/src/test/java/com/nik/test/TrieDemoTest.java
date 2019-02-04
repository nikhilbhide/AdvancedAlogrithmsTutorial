package com.nik.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.nik.tutorial.trie.TrieDemo;

public class TrieDemoTest {
	private TrieDemo instance;

	@Before
	public void setup() {
		instance = new TrieDemo();
	}

	@After
	public void tearDown() {
		instance = null;
	}

	@Test 
	public void testTrieInsertSimpleSuccess() {
		Boolean inserted = instance.insert("abc");
		Assert.assertTrue(inserted);
	}

	@Test 
	public void testTrieInsertComplexSuccess() {
		Boolean inserted = instance.insert("abcdefg");
		Assert.assertTrue(inserted);
	}
	
	@Test 
	public void testTrieMultipleInsertsComplexCheckSizeSuccess() {
		Boolean inserted = instance.insert("abcdefg");
		Assert.assertTrue(inserted);
		
		inserted = instance.insert("abch");
		Assert.assertTrue(inserted);
		
		int numOfNodes = instance.getSize();
		Assert.assertTrue(numOfNodes==8);
	}
	
	@Test 
	public void testDelete() {
		Boolean inserted = instance.insert("abcdefg");
		Assert.assertTrue(inserted);
		
		inserted = instance.insert("abch");
		Assert.assertTrue(inserted);
		
		boolean deleteSuccesful = instance.delete("abcdefg",false);
		Assert.assertTrue(deleteSuccesful);		
		
		int numOfNodes = instance.getSize();
		Assert.assertTrue(numOfNodes==4);
	}
}