package com.nik.tutorial.trie;

import java.util.Collection;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

/**
 * Trie data structure implementation
 * 
 * @author nikhil.bhide
 *
 */
public class TrieDemo {
	private TrieNode root = new TrieNode();

	/**Searches the given key in the trie data structure by traversing through the character nodes.
	 * 
	 * @param key The string to be searched 
	 * 
	 * @return <code>true</code> if word is found
	 */
	public Boolean search(String key) {
		boolean wordExists = false;
		if(root.getCharacterMap()==null) {
			return false;
		}
		else {
			TrieNode currentNode = root;
			for(char letter:key.toCharArray()) {
				TrieNode nextNode = currentNode.getCharacterMap().get(letter);
				if(nextNode!=null) {
					currentNode = currentNode.getCharacterMap().get(letter);
				}
				else {
					break;
				}
			}

			//Check whether key exists and last character node denotes a "whole" word
			if(currentNode.getId() == key.charAt(key.length()-1) && currentNode.isEndOfWord())  {
				wordExists = true;
			}
		}
		return wordExists;
	}


	/**Inserts the given key in the trie data structure by traversing through the character nodes.
	 * It first checks whether the 
	 * 
	 * @param key The string to be searched 
	 * 
	 * @return <code>true</code> if word is found
	 */
	public Boolean insert(String key) {
		boolean wordExists = search(key);
		if(!wordExists) {
			TrieNode currentNode = root;
			for(char letter:key.toCharArray()) {
				TrieNode nextNode = currentNode.getCharacterMap().get(letter);
				if(nextNode==null) {
					nextNode = new TrieNode();
					nextNode.setId(letter);
					currentNode.getCharacterMap().put(letter, nextNode);
				}

				currentNode = nextNode;
			}
		}	
		return true;
	}


	/**
	 * Finds the size of the trie data structure.
	 * It takes into consideration all of the nodes expect root node. Root node is just a placeholder that holds references to first level nodes.
	 * The time complexity is O(average length of keys * number of eys)
	 * 
	 * @return The length of the trie data structure
	 */
	public int getSize() {
		int size = 0;
		Queue<TrieNode> queue = new LinkedList();

		queue.add(root);
		while(!queue.isEmpty()) {
			TrieNode node = queue.remove();
			Collection<TrieNode> children = node.getCharacterMap().values();
			size = size + children.size();
			queue.addAll(children);
		}

		return size;
	}


	/**
	 * Deleting the char nodes associated with the letters of word to be deleted.
	 * 
	 * @param key The key to be deleted 
	 * @param isPattern If its for the patter then delete the entire path after pattern
	 * 
	 * @return <code>true</code> if delete operation is successful
	 */
	public boolean delete(String key, boolean isPattern) {
		TrieNode currentNode = root;
		Stack<TrieNode> nodeStack = new Stack<TrieNode>();

		for(char letter:key.toCharArray()) {
			TrieNode nextNode = currentNode.getCharacterMap().get(letter);
			if(nextNode==null) {
				if(isPattern)
					throw new NoSuchElementException("Such pattern does not exist");
				else 
					throw new NoSuchElementException("Such key does not exist");
			}
			else {
				nodeStack.push(nextNode);
				currentNode = nextNode;
			}
		}

		int counter = 0;

		//Check trie nodes. Delete the node if it does not have any children otherwise just change the end of word flag. 
		while(!nodeStack.isEmpty()) {
			TrieNode tempNode = nodeStack.pop();
			
			if(counter==0) {
				tempNode.setEndOfWord(true);
			}
			
			if(tempNode.getCharacterMap().size()==0) {
				if(nodeStack.peek()!=null) {
					nodeStack.peek().getCharacterMap().remove(tempNode.getId());
				}				
			}
			else {
				break;
			}
		
			counter++;
		}

		return true;
	}
}