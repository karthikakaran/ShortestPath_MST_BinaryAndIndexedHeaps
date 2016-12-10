//===========================================================================================================================
//Program : To find the shortest path 
//===========================================================================================================================
//@author: Karthika
//	Date created: 2016/11/13
//===========================================================================================================================
/* Ver 1.0: Starter code for Dijkstra's Shortest path algorithm */

import java.util.Scanner;
import java.lang.Comparable;
import java.io.FileNotFoundException;
import java.io.File;

public class ShortestPath {
	static final int Infinity = Integer.MAX_VALUE;

	/** Method to perform shortest distance using Dijkstra's algorith
	 * @param g : graph : to find the shortest distance for
	 * @param s : vertex : source to start 
	 */
	static void DijkstraShortestPaths(Graph g, Vertex s) {
		//Set distance as infinity, parent as null expect source vertex
		//intree as false after running Prims
		for (Vertex vertex : g) {
			vertex.d = Infinity;
			vertex.p = null;
			vertex.inTree = false;
		}
		s.d = 0;
		s.p = null;
		
		//Set the indices of the vertex and store them in array for indexed heap
		Vertex[] vertexArray = new Vertex[g.size + 1];
		int index = 1;
		for (Vertex v : g) {
			vertexArray[index] = v;
			vertexArray[index].index = index;
			index++;
		}
		
		//Heap is constructed based on distance as priority
		IndexedHeap<Vertex> ind = new IndexedHeap<>(vertexArray, s);
		ind.size = g.size;
		//Decrease the source vertex
		ind.decreaseKey(s);
		//Till the heap is not empty
		while (ind.peek() != null) {
			//delete min vertex and ass it to tree
			Vertex u = ind.deleteMin();
			u.inTree = true;
			//Edges that are adjacent to the vertex
			for (Edge e : u.adj) {
				Vertex v = e.otherEnd(u);
				//relax the to vertex with new distance if edge weight plus the from vertex distance is less than the 
				// to vertex distance
				if (!v.inTree && u.d + e.weight < v.d) {
					v.d = u.d + e.weight;
					v.p = u;
					//decrease the key and relocate the vertex in the indexed heap
					ind.decreaseKey(v);
				}
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in;

		if (args.length > 0) {
			File inputFile = new File(args[0]);
			in = new Scanner(inputFile);
		} else {
			in = new Scanner(System.in);
		}

		Graph g = Graph.readGraph(in);
		int src = in.nextInt();
		int target = in.nextInt();
		Vertex s = g.getVertex(src);
		Vertex t = g.getVertex(target);
		DijkstraShortestPaths(g, s);

		System.out.println("Shortest path :: " + t.d);
	}
}
