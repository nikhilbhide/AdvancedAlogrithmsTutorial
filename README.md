# AdvancedAlogrithmsTutorial
This repository includes implementation of different advaned algorithms in Java/Scala.
As of now, following algorithms are implemented
1. CircularQueue of any size - This algorithm detects the cycle in the connected components. Its the circular queue nested in a queue.
2. Graph Breadth First Traversal - Breadth first traversal algorithm using Queue.
3. Graph Depth First Traversal - Depth first traversal algorithm using Stack.
4. Detect cycle in the graph - This algorithm detects cycle in the graph using DFS
5. Maze Runner - This is the implementation of Maze Runner problem. This is the implementation of Maze Runner problem. A Maze is given as N*N binary matrix of blocks where source block is represented by number 2 and destination block by number 3. 0 means close door and 1 means open door.
6. Dijkstra's algorithm - Shortest path in graph algorithm 
7. Bellman Ford algorithm - Dijkstra's algorithm can not handle negative edge weights. In this case, Bellman Ford algorithm comes handy.
A graph having negative weights is used in stock market, forex arbitrage, chemical reaction.
8. Longest Path Traversal - Bellman Ford algorithm is used to find longest path in the graph. One change is required that is to negate the weights. 
9. Kruskal's algorithm - Kruskal's algorithm generates MST from a given graph. MST is used heavily in applications such as routing, K means clustring, mobile tower placements, maps etc. It requires all edges in a sorted order. Its also known as lazy way to generate MST.
Time complexity is O(ELOGV).
10. Prim's algorithm - Like Kruskal's algorithm, Prim's algorithm generates MST from a given graph. It can start from any vertex. Its also called as eager way to generate MST. Time complexity is O(ELogV).
11. Merge Sort - Merge sort is popular algorithm to sort the given data. It works on divide and conquer algorithm and its not in-place algorithm. Time complexity of this algorithm is O(nlogn).
12. Quick Select - Quick select algorithm finds kth smallest element. Average time complexity is O(n) and worst case time complexy is O(n^2).
13. Topological sorting - Ordering of vertices in the topological sequence. In this order, all the vertices having indegree 0 are traversed first and then next level of vertices is covered. Vertices having no depedencies are covered first and vertices are traversed into DFS order.
14. Insertion sorting - Implementation of insertion sorting in which a element is compared with all of the elements on the left.
And swap the elements whenever order is breached.
15.Kosarajus Algorithm - Solution to strongly connected components.
16. Cutting Rod - Dynamic programming solution to cutting rod problem. A rod with length n is divided into different lengths. And each length is associated with price. So solution is to consider 
17. Trie Data Structure - Implementation of trie data structure - insert, delete, search operations
