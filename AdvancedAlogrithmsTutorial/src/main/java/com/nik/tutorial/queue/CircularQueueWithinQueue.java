package com.nik.tutorial.queue;

import java.util.Optional;

/**
 * @author nikhil.bhide
 *
 * @param <T> The generic type to be used for queue implementation
 */
public class CircularQueueWithinQueue<T> {
	private Node headNode;
	private Node lastNode;
	private int size;

	/**
	 * Inserts elements in queue that allows cycle of any size
	 * 
	 * @param item The element to be added in a queue.
	 * @param nextNodeIndex The position of node that will be added as a next node
	 */
	public void add(T item, Optional<Integer> nextNodeIndex) {
		if(!isNextNodeIndexValid(nextNodeIndex)) {
			throw new IllegalArgumentException("Next node index can not be greater than size of the queue");
		}
		else {
			if(headNode==null) {
				headNode = new Node(item);
				lastNode = headNode;
			}
			else {
				Node node = new Node(item);
				lastNode.setNext(node);
				lastNode = node;
			}
			if(nextNodeIndex!=null && nextNodeIndex.isPresent()) {
				Node node = getNodeByIndex(nextNodeIndex.get().intValue());
				lastNode.setNext(node);
			}
		}
		size++;
	}


	/**
	 * Retrieves the Node object corresponding to index position
	 * 
	 * @param index The position of a node in the queue
	 * @return The node object corresponding to a particular position
	 */
	private Node getNodeByIndex(int index) {
		Node currentNode = headNode;
		int counter=0;
		while(currentNode!=null) {
			counter++;
			if(counter==index) {
				break;			
			}
			currentNode = currentNode.getNext();
		}
		return currentNode;		
	}

	/**
	 * Checks whether next node is a valid provided position is less than size
	 * 
	 * @param nextNodeIndex
	 * @return <code>true</code> if next node is a valid
	 */
	private boolean isNextNodeIndexValid(Optional<Integer> nextNodeIndex) {
		if(nextNodeIndex!=null && nextNodeIndex.isPresent()) {
			int next = nextNodeIndex.get();
			if(next>size-1||next<0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks whether a given queue is a circular
	 * 
	 * @return <code>true</code> if given queue consists of a cycle
	 */
	public boolean isCircularQueue() {
		if(headNode!=null && headNode.getNext()!=null) {
			Node currentNode = headNode;
			Node nextNode = headNode;
			while(currentNode.getNext()!=null && currentNode.getNext().getNext()!=null) {
				Node node1 = currentNode;
				Node node2 = nextNode.getNext().getNext();
				if(node1==node2) {
					System.out.println("Circular Queue");
					return true;
				}
				nextNode = nextNode.getNext().getNext();
				currentNode = currentNode.getNext();
			}
		}
		return false;
	}
}