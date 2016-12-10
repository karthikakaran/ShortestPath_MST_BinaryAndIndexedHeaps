//===========================================================================================================================
//Program : To find Minimum spanning tree using Prims algorithm for both edge and vertex versions
//===========================================================================================================================
//@author: Karthika
//	Date created: 2016/11/13
//===========================================================================================================================
/* Ver 1.0: Starter code for Prim's MST algorithm */

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class MST {
	static final int Infinity = Integer.MAX_VALUE;
	
	/** Method to find the minimum spanning tree using Prims algorithm with edges
	 * @param g : graph : to find the MST for
	 * @param s : vertex : source to start 
	 */
	static int PrimMSTEdge(Graph g, Vertex s) {
		//weight of final MST
		int wmst = 0;
		//JAVA priority queue is used for this		
		PriorityQueue<Edge> pq = new PriorityQueue<>();

		//source is added to tree and adjacent vertices added to the queue
		s.inTree = true;
		for (Edge e : s.adj)
			pq.add(e);
		
		//till the heap is not empty
		while (!pq.isEmpty()) {
			//Edge with min weight is removed 
			Edge e = pq.remove();
			if (e.from.inTree && e.to.inTree)
				continue;
			//Adjacent edges are added and the min weight is added to total weight
			if (e.from.inTree) {
				wmst += e.weight;
				e.to.inTree = true;
				e.to.p = e.from;
				for (Edge f : e.to.adj)
					pq.add(f);
			} else {
				wmst += e.weight;
				e.from.inTree = true;
				e.from.p = e.to;
				for (Edge f : e.from.adj)
					pq.add(f);
			}
		}
		return wmst;
	}
	
	/** Method to find the minimum spanning tree using Prims algorithm with vertices
	 * @param g : graph : to find the MST for
	 * @param s : vertex : source to start 
	 */
	static int PrimMST(Graph g, Vertex s) {
		int wmst = 0;
		Comparator<Vertex> comp = s;
		Vertex[] vertexArray = new Vertex[g.size + 1];
		
		//Set distance as infinity, parent as null expect source vertex
		//intree as false after running Prims
		for (Vertex vertex : g) {
			vertex.d = Infinity;
			vertex.p = null;
		}
		
		s.d = 0;
		s.p = null;
		
		//Set the indices of the vertex and store them in array for indexed heap
		for (int index = 1; index < g.size + 1; index++) {
			vertexArray[index] = g.getVertex(index);
			vertexArray[index].index = index;
		}
		//Heap is constructed based on distance as priority
		IndexedHeap<Vertex> ind = new IndexedHeap<>(vertexArray, comp);
		ind.size = g.size;
		//Decrease the source vertex
		ind.decreaseKey(s);
		//Till the heap is not empty
		while (ind.size > 0) {
			//delete min vertex and ass it to tree
			Vertex ver = ind.deleteMin();
			ver.inTree = true;
			//Edges that are adjacent to the vertex and the min weights are added to the MST weight
			wmst += ver.d;
			for (Edge edge : ver.adj) {
				Vertex vt = edge.otherEnd(ver);
				//relax the to vertex with new distance if edge weight is less than the 
				// to vertex distance
				if (!vt.inTree && vt.d > edge.weight) {
					vt.d = edge.weight;
					vt.p = ver;
					//decrease the key and relocate the vertex in the indexed heap
					ind.decreaseKey(vt);
				}
			}
		}
		
		return wmst;
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
		Vertex s = g.getVertex(1);
		Timer timer = new Timer();
		System.out.println("PrimMST-Edge weight :: " + PrimMSTEdge(g, s));
		System.out.println(timer.end());
	}
}
