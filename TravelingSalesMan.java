//===========================================================================================================================
//Program : To find the vertices to be visited in order for a Traveling Sales Man problem
//===========================================================================================================================
//@author: Karthika
//	Date created: 2016/11/13
//===========================================================================================================================
import java.util.ArrayList;

public class TravelingSalesMan {

	private static  ArrayList<Vertex> tspVertices = new ArrayList<>();
	
	/** Method to find tsp
	 *  @param g : graph - to find tsp for
	 *  @param s : source vertex to start from
	 */
	public static void tsp(Graph g, Vertex s, int VERBOSE) {
		//find the MST
		int mst = MST.PrimMST(g, s);
		//DFS to visit all vertices and enumerate them by storing in a order in the array
		dfs(g, s);
		//Print the tsp vertices in order
		if (VERBOSE > 0) {
			System.out.print("TSP path :: ");
			for(Vertex v : tspVertices) {
				System.out.print(v.name + " ");
			}
			System.out.println();
		}
	}
	
	/** Method to perform dfs
	 *  @param g : graph - to visit all the vertices of the graph
	 *  @param s : source vertex to start from
	 */
	public static void dfs(Graph g, Vertex src) {
		for(Vertex v : g) {
			v.seen = false;
		}
		//visit each vertex
		for(Vertex v : g) {
			if(!v.seen) {
				//set the vertex as seen
				v.seen = true;
				tspVertices.add(v);
				dfsVisit(v);
			}
		}
	}
	
	/** Method for helper function of dfs
	 *  @param v : source vertex to start from
	 */
	public static void dfsVisit(Vertex u) {
		//Edges adjacent to the vertex
		for (Edge e : u.adj) {
			Vertex vert = e.otherEnd(u);
			//set the parent to the vertex and consider only unseen vertices
			if (vert.p == u && !vert.seen) {
				//set the vertex as seen
				vert.seen = true;
				//add in the visited order
				tspVertices.add(vert);
				//recursively call dfs to visit adjacent vertices
				dfsVisit(vert);
			}
		}
	}
}
