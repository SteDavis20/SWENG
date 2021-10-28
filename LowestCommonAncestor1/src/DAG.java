// @author: Stephen Davis
// code is adapted from code provided by @Robert Sedgewick in his textbook Algorithms 4th Edition.

import java.util.ArrayList;

class Node {
	int data;
	
    Node(int value) {
        this.data = value;
    }
}

public class DAG
{

	/**
	 *  @author Robert Sedgewick
	 *  @author Kevin Wayne
	 */

	private static final String NEWLINE = System.getProperty("line.separator");

	private final int V;           // number of vertices in this DAG
	private int E;                 // number of edges in this DAG
	private Bag<Integer>[] adj;    // adj[v] = adjacency list for vertex v

	private boolean[] marked;  // marked[v] = true iff v is reachable from source(s)

	Node root;

	/**
	 * Initializes an empty DAG with V vertices.
	 *
	 * @param  V the number of vertices
	 * @throws IllegalArgumentException if {@code V < 0}
	 */
	public DAG(int V) {
		if (V < 0) throw new IllegalArgumentException("Number of vertices in a DAG must be non-negative");
		this.V = V;
		this.E = 0;
		//	        indegree = new int[V];
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<Integer>();
		}
		if(V>0) {
			root = new Node(0);
		}
	}

	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	public void addEdge(int v, int w) {
		adj[v].add(w);
		E++;
	}

	public Iterable<Integer> adj(int v) {
		return adj[v];
	}

	/**
	 * @return the number of vertices V, followed by the number of edges E,  
	 *         followed by the V adjacency lists
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " vertices, " + E + " edges " + NEWLINE);
		for (int v = 0; v < V; v++) {
			s.append(String.format("%d: ", v));
			for (int w : adj[v]) {
				s.append(String.format("%d ", w));
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}

	// LCA comes before the 2 nodes, so traverse graph backwards
	public DAG reverse() {
		DAG reverse = new DAG(V);
		for (int v = 0; v < V; v++) {
			for (int w : adj(v)) {
				reverse.addEdge(w, v);
			}
		}
		return reverse;
	}

	// Everything from here is Stephen Davis' work. 
	//@author: Stephen Davis
	public ArrayList<Integer> DFS(int s) {
		ArrayList<Integer> path = new ArrayList<Integer>();
		marked = new boolean[this.V()];
        path = dfs(path, s);
        return path;
    }
	
	public ArrayList<Integer> dfs(ArrayList<Integer> path, int v) {
		marked[v] = true;
		path.add(v);
		for (int w : this.adj(v)) {
			if (!marked[w]) dfs(path, w);
		}
		return path;	
	}

	// -------------------------------------------------------------------------------------------------------------

	/* My Altered Approach to the following Source: https://www.baeldung.com/cs/lowest-common-ancestor-acyclic-graph
	 * 
	 * variable names should explain the code sufficiently.
	 * 
	 * Note: we first reverse the graph so we can easily access a given node's ancestors via depth-first search.
	 */

	private int findLCAHelper(Node v, Node w) {
		DAG reversedGraph = this.reverse();
		
		ArrayList<Integer> vPath = reversedGraph.DFS(v.data);
		ArrayList<Integer> wPath = reversedGraph.DFS(w.data);
		ArrayList<Integer> commonAncestors = new ArrayList<Integer>();
		
		for(Integer ancestorOfV : vPath) {
			for(Integer ancestorOfW : wPath) {
				if(ancestorOfV == ancestorOfW) {
					commonAncestors.add(ancestorOfV);	// 1st common ancestor will be "lowest" since we reversed graph
					return commonAncestors.get(0);
				}
			}
		}
		return -1;
	}

	public int findLCA(Node v, Node w) {
		if(root == null) {
			return -1;
		}

		if((v.data < 0 || v.data >= V)) {
			return -1;
		}

		if((w.data < 0 || w.data >= V)) {
			return -1;
		}

		if(v.data==w.data) {
			return v.data;
		}

		// if no cycles exist proceed
//		if() {
//			return -1;	
//		}
		
		int lca = findLCAHelper(v, w);
		return lca;
	}
			
	public void populateShortGraph() {
		this.addEdge(0, 1);
		this.addEdge(0, 2);
	}
	
	public void populateIsolatedVertexGraph() {
		
		
	}

	public void populateCycleGraph() {
		
		
	}

	public void populateDisjointSubGraphs() {
		
	
	}
	
	
	public void populatenoEdgesGraph() {
		
		
	}
	
	public void populateLongNarrowGraph(DAG graph) {
		for(int i=0; i<V-1; i++) {
			graph.addEdge(i, i+1);
		}
	}

	//							0
	//						/		\
	//					   1		 6
	//					  /  \		/
	//					 4    5	   2
	//					 	 /
	//					    3
	//	
	public static void main(String[] args) {
//		DAG shortGraph = new DAG(3);
//		shortGraph.populateShortGraph();
//		String string = shortGraph.toString();
//		System.out.println(""+string);
		
//		DAG singleNodeGraph = new DAG(1);
//		String string = singleNodeGraph.toString();
//		System.out.println(""+string);
		
//				DAG graph = new DAG(7);
//				graph.addEdge(0, 1);
//				graph.addEdge(1, 4);
//				graph.addEdge(1, 5);
//				graph.addEdge(5, 3);
//				graph.addEdge(0, 6);
//				graph.addEdge(6, 2);
//				
//				Node n1 = new Node(3);
//				Node n2 = new Node(4);
//				int lca = graph.findLCA(n1,n2);
//				System.out.println("Expecting LCA of 3 and 4 to be 1... ");
//				System.out.println("LCA of 3 and 4: "+lca);
	}

}