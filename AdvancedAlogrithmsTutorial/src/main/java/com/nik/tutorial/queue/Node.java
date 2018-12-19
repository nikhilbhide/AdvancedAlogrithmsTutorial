package com.nik.tutorial.queue;

/**
 * This class provides implementation for a node of a singly linked list
 * 
 * @author nikhil.bhide
 *
 * @param <T> The generic type to be used for node implementation
 */

public class Node <T> {
	private Node next;
	private T element;
	
	public Node(T element) {
		this.element = element;
	}
	
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	public T getElement() {
		return element;
	}
	public void setElement(T element) {
		this.element = element;
	}
}