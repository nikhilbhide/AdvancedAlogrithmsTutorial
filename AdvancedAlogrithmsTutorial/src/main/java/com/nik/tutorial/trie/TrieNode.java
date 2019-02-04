package com.nik.tutorial.trie;

import java.util.concurrent.ConcurrentHashMap;

public class TrieNode {
	private char id;
	private ConcurrentHashMap<Character, TrieNode> characterMap;
	private boolean isEndOfWord;
	
	public TrieNode() {
		characterMap = new ConcurrentHashMap();
	}
	
	public char getId() {
		return id;
	}
	public void setId(char id) {
		this.id = id;
	}
	public ConcurrentHashMap<Character, TrieNode> getCharacterMap() {
		return characterMap;
	}
	public void setCharacterMap(ConcurrentHashMap<Character, TrieNode> characterMap) {
		this.characterMap = characterMap;
	}
	public boolean isEndOfWord() {
		return isEndOfWord;
	}
	public void setEndOfWord(boolean isEndOfWord) {
		this.isEndOfWord = isEndOfWord;
	}
}