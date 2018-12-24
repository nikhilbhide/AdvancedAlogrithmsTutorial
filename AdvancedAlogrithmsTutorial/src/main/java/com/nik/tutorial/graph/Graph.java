package com.nik.tutorial.graph;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class is implementation of Graph.
 * It provides implementation of different apis of Graph such as BFS, DFS, Spanning Tree etc.
 * 
 * @author nikhil.bhide
 *
 */
public class Graph {
	private ConcurrentHashMap<String, String[]> edgeMap;
	private ConcurrentHashMap<String, Vertex> vertexMap;

	public Graph () {
		edgeMap = new ConcurrentHashMap();
		vertexMap = new ConcurrentHashMap();
	}

	/**
	 * Populates the graph based on details of vertices and edges
	 * 
	 * @param vertexMap The map of vertexname and vertex object
	 * @param edgeMap The map of vertex name and edgelist
	 */
	public void createGraph(ConcurrentHashMap<String,Vertex> vertexMap, ConcurrentHashMap<String, String[]> edgeMap) {
		this.edgeMap = edgeMap;
		this.vertexMap = vertexMap;

		edgeMap.forEach((k,v)-> {
			Vertex vertex = vertexMap.get(k);
			for(String vertexName:v) {
				Vertex neighbour = vertexMap.get(vertexName);
				vertex.getVertices().add(neighbour);
			}
		});
	}

	/**
	 * Traverses graph in breadth first order. 
	 * 
	 * @param queue The queue of visited nodes. We require a datastructure that can maintain the order of nodes in FIFO
	 * @param result The sequential hash set that contains the result of the nodes visited
	 */
	public void traverseBFS(Queue<Vertex> queue, LinkedHashSet<String> result) {
		Vertex visited = queue.remove();
		List<Vertex> neighboringVertices = visited.getVertices();
		result.add(visited.getName());

		for(Vertex vertex:neighboringVertices) {
			if(!result.contains(vertex.getName())) {
				queue.add(vertex);
			}
		}

		if(!queue.isEmpty()) {
			traverseBFS(queue, result);
		}
	}

	/**
	 * Traverses graph in breadth first order. 
	 * 
	 * @param startVertex The starting vertex of a traversal
	 * 
	 * @return The list containing the traversed path
	 */
	public LinkedHashSet <String> traverseBFS(String startVertex) {
		Vertex vertex = vertexMap.get(startVertex);
		LinkedHashSet <String> result = new LinkedHashSet ();

		if(vertex!=null) {
			Queue<Vertex> queue = new LinkedList();
			queue.add(vertex);
			result.add(vertex.getName());
			traverseBFS(queue, result);
		}
		return result;
	}

	/**
	 * Traverses graph in depth first order. 
	 * 
	 * @param currentVertex The current vertex of a traversal
	 * @param visited The intermediate object to keep track of visited nodes. This is to avoid cyclic graph case
	 * @param result The sequential hash set that contains the result of the nodes visited
	 */
	public void traverseDFS(Vertex currentVertex, LinkedHashSet<String> visited, LinkedHashSet<String> result) {
		List<Vertex> neighboringVertices = currentVertex.getVertices();
		visited.add(currentVertex.getName());

		for(Vertex vertex:neighboringVertices) {
			if(!visited.contains(vertex.getName())) {
				traverseDFS(vertex,visited,result);
			}
		}
		result.add(currentVertex.getName());
	}

	/**
	 * Traverses graph in breadth first order. 
	 * 
	 * @param startVertex The starting vertex of a traversal
	 * 
	 * @return The list containing the path details

	 */
	public LinkedHashSet <String> traverseDFS(String startVertex) {
		Vertex vertex = vertexMap.get(startVertex);
		LinkedHashSet <String> result = new LinkedHashSet ();
		LinkedHashSet <String> visited = new LinkedHashSet ();

		if(vertex!=null) {
			traverseDFS(vertex,visited,result);
		}
		return result;
	}

	/**
	 * Detects any cycle in the graph
	 * 
	 * @param currentVertex The current vertex under traversal
	 * @param visited The stack that keeps track of vertices under traversal
	 * @param result The list of visited vertices 
	 * 
	 * @return <code>True</code> if cycle is present
	 */
	public boolean detectCycles(Vertex currentVertex, Stack<String> visited, LinkedHashSet<String> result) {
		List<Vertex> neighboringVertices = currentVertex.getVertices();
		visited.push(currentVertex.getName());

		for(Vertex vertex:neighboringVertices) {
			if(!visited.contains(vertex.getName())) {
				if(!result.contains(currentVertex.getName())) {
					return detectCycles(vertex,visited,result);
				}
			}
			else {
				return true;				
			}
		}
		visited.pop();
		result.add(currentVertex.getName());
		return false;
	}

	public boolean isCyclic() {
		return detectCycles(vertexMap.get("A"), new Stack<String>(),new LinkedHashSet<String>());
	}

	/**
	 * Traverses the maze as per following rules.
	 * Start point is 2 
	 * End point is 3
	 * Door close is represented by 0
	 * Door open is represented by 1
	 * @param maze 2-Dimensional array that represents adjacency matrix
	 * @param result The traversed path represented by row->column list
	 * @param visitedRows The list containing list of visited row position
	 * @param visitedColumns The list containing list of visited column position
	 * @param stack The stack representing the vertices of ongoing traversal
	 * @param row The current row
	 * @param column The current column
	 * @param totalRows Total number of rows
	 * @param totalColumns Total number of columns
	*/
	public void traverseMaze(int[][] maze, ArrayList<String> result, ArrayList<Integer> visitedRows, ArrayList<Integer> visitedColumns, Stack<String> stack, int row, int column, int totalRows, int totalColumns) {
		if(((row<totalRows &&row>=0) && (column<totalColumns && column>=0)) && !((visitedRows.contains(row) && visitedColumns.contains(column)))) {
			int element = maze[row][column];
			if(element!=0) {
				stack.push(row+"->"+column);
				visitedRows.add(row);
				visitedColumns.add(column);
				if(element==3) {
					stack.stream().forEach(item -> {
						result.add(item);
					});
				}
				else {
					traverseMaze(maze,result, visitedRows,visitedColumns,stack,row+1,column,totalColumns,totalRows);
					traverseMaze(maze,result, visitedRows,visitedColumns,stack,row,column+1,totalColumns,totalRows);
					traverseMaze(maze,result, visitedRows,visitedColumns,stack,row-1,column,totalColumns,totalRows);
					traverseMaze(maze,result, visitedRows,visitedColumns,stack,row,column-1,totalColumns,totalRows);	
				}
				stack.pop();
			}
		}	
	}

	/**
	 * Traverses the maze as per following rules.
	 * Start point is 2 
	 * End point is 3
	 * Door close is represented by 0
	 * Door open is represented by 1
	 * 
	 * @param maze The map with vertextName {@link String} and vertexObject {@link Vertex}
	 * @return The array of row and column
	 */
	public ArrayList<String> traverseMaze(int[][] maze) {
		int[] posResult = GraphUtility.getStartIndex(maze);
		if(posResult!=null) {
			ArrayList<String> result = new ArrayList();
			ArrayList<Integer> visitedRows = new ArrayList();
			ArrayList<Integer> visitedColumns = new ArrayList();


			Stack<String> stack = new Stack();
			traverseMaze(maze, result, visitedRows, visitedColumns, stack, posResult[0],posResult[1],maze.length,maze[0].length);
			return result;
		}
		return null;
	}
}