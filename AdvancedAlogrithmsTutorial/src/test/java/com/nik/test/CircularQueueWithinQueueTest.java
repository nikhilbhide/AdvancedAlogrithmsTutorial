package com.nik.test;

import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nik.tutorial.queue.CircularQueueWithinQueue;

public class CircularQueueWithinQueueTest {
	CircularQueueWithinQueue instance;

	@Before
	public void init() {
		instance = new CircularQueueWithinQueue();
	}

	@After
	public void postTestExecution() {
		instance = null;
	}

	@Test
	public void detectCircularQueueSuccess() {
		instance.add("One",null);
		instance.add("Two",null);
		instance.add("Three",null);
		instance.add("Four",null);
		instance.add("Five",null);
		Optional<Integer> index = Optional.of(new Integer(1));
		instance.add("Six",index);

		Assert.assertTrue(instance.isCircularQueue());	
	}

	@Test
	public void detectNoCircularQueueSuccess() {

	}
}
