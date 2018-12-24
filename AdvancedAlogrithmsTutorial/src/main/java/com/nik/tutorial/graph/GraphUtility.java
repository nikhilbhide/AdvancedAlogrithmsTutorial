package com.nik.tutorial.graph;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Utility class that provides different functions for a graph
 * 
 * @author nikhil.bhide
 */

public class GraphUtility {
	/**
	 * Creates map of vertex name and {@link Vertex} object
	 * 
	 * @param vertices The array of vertices of graph
	 * @return vertexMap The map with vertextName {@link String} and vertexObject {@link Vertex}
	 */
	public static ConcurrentHashMap<String,Vertex> getVertexMap(String[] vertices) {
		ConcurrentHashMap<String,Vertex> vertexMap = null;
		if(vertices!=null) {
			vertexMap = new ConcurrentHashMap<String,Vertex>();
			for(String vertexName:vertices) {
				Vertex vertex = new  Vertex(vertexName);
				vertexMap.put(vertexName, vertex);
			}
		}
		return vertexMap;
	}

	/**
	 * Populates the edges of a vertex object based on edge map provided
	 * 
	 * @param vertexMap The map with vertextName {@link String} and vertexObject {@link Vertex}
	 * @param edgeMap he map with vertextName {@link String} and edgeArray {@link String[]}
	 */
	public static void populateEdgeList(ConcurrentHashMap<String,Vertex> vertexMap,ConcurrentHashMap<String, String[]> edgeMap) {
		if(vertexMap!=null) {
			vertexMap.forEach((k,v)-> {
				String[] edgeVertices = edgeMap.get(k);
				for(String edgeVertex:edgeVertices) {
					v.getVertices().add(vertexMap.get(k));
				}
			});
		}
	}

	/**
	 * Populates the adjacency matrix from the lines 
	 * 
	 * @param lines The map with vertextName {@link String} and vertexObject {@link Vertex}
	 */
	public static int[][] populateMazeRunner(ArrayList<String> lines) {
		if(lines!=null && lines.get(0)!=null) {
			int width = getWidthOfMaze(lines.get(0));
			int breadth = getbreadthOfMaze(lines.get(0));
			int [][]  mazeRunner = new int[width][breadth];
			int rowCounter = 0;

			for(String line:lines) {
				String[] elements = line.split(" ");
				if(elements!=null) {
					if(!isRowLengthValid(width,elements.length)) {
						throw new IllegalArgumentException("Maze is invalid");
					}

					int[] row = parseMaze(elements);
					mazeRunner[rowCounter] = row;
					rowCounter++;
				}};
				return mazeRunner;
		}
		return null;
	}

	private static boolean isRowLengthValid(int width, int currentWidth) {
		return width==currentWidth;
	}

	/**
	 * Parse the provided structure to verify whether the structure conforms to the maze format
	 * Start point is 2 
	 * End point is 3
	 * Door close is represented by 0
	 * Door open is represented by 1
	 * 
	 * @param elements The input structure
	 * 
	 * @return The colunmnary array against a specific row
	 */
	private static int[] parseMaze(String[] elements) {
		int[] row = new int[elements.length];
		int counter = 0;

		for(String item:elements) {
			int cell = Integer.parseInt(item);
			if(cell<3 || cell>0) {
				row[counter] = cell;
				counter++;
			}
			else {
				throw new IllegalArgumentException("Invalid Maze");
			}
		}
		return row;
	}

	/**
	 * Retrieve breadth of the maze 
	 * 
	 * @param line The first line of the file contents
	 * 
	 * @return size of the maze row

	 */
	private static int getbreadthOfMaze(String line) {
		if(line!=null) {
			String [] elements = line.split(" ");
			return elements.length;
		}
		else {
			return 0;
		}
	}

	/**
	 * Get number of rows of maze
	 * 
	 * @param line The first line of the file contents
	 * 
	 * @return size of the maze column
	 */
	private static int getWidthOfMaze(String line) {
		return line.split(" ").length;
	}


	/**
	 * Retrieve starting position in the maze
	 * Start point is denoted by 2 

	 * @param maze 2-Dimensional array that represents the maze
	 * 
	 * @return Position array in which row is stored at index 0 and column is stored at index 1
	 */
	public static int[] getStartIndex(int[][] maze) {
		for(int rowCounter=0;rowCounter<maze.length;rowCounter++) {
			for(int columnCounter=0; columnCounter<maze[0].length;columnCounter++) {
				if(maze[rowCounter][columnCounter]==2) {
					int[] posResult = new int[2];
					posResult[0] = rowCounter;
					posResult[1] = columnCounter;
					return posResult;
				}
			}
		}
		return null;
	}
}