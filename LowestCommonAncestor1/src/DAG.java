// @author: Stephen Davis

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
	private int count;         // number of vertices reachable from source(s)

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

	/* Source: https://www.baeldung.com/cs/lowest-common-ancestor-acyclic-graph
	 * 
	 * Suppose we want to find the LCA(u, v) in graph G. Initially, all the vertices are colored white.
	 * 
	 *	First, we do a Depth-First Search (DFS) on one of the target nodes. Let it be node u. Also, we�ll keep track
	 *	of the parent�s array (current path from a starting vertex). During the DFS, we color all the ancestors of u
	 *	in red each time we reach it.

	 *	Second, we should start the DFS on the other node v. When we reach it, we recolor all red ancestors of v in
	 *	black.

	 *  Finally, we build a subgraph, induced by the black nodes. The nodes in a new graph with zero out-degrees are
	 *	the answers. 

	 */

	private int findLCAHelper(Node v, Node w) {
		boolean foundLCA = false;
		DAG reversedGraph = this.reverse();

		// keep track of parent's array
		ArrayList<Integer> vPath = reversedGraph.DFS(v.data);
		ArrayList<Integer> wPath = reversedGraph.DFS(w.data);

		// Build subgraph
		ArrayList<Integer> commonAncestors = new ArrayList<Integer>();
		
		for(Integer ancestorOfV : vPath) {
			for(Integer ancestorOfW : wPath) {
				if(ancestorOfV == ancestorOfW) {
					commonAncestors.add(ancestorOfV);
					foundLCA = true;
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

		// Check v is valid Node
		if((v.data < 0 || v.data >= V)) {
			return -1;
		}

		// Check w is valid Node
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
		//		return lca;
	}

	// @author: Stephen Davis
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
				DAG graph = new DAG(7);
				graph.addEdge(0, 1);
				graph.addEdge(1, 4);
				graph.addEdge(1, 5);
				graph.addEdge(5, 3);
				graph.addEdge(0, 6);
				graph.addEdge(6, 2);
				
				Node n1 = new Node(3);
				Node n2 = new Node(4);
				int lca = graph.findLCA(n1,n2);
				System.out.println("Expecting LCA of 3 and 4 to be 1... ");
				System.out.println("LCA of 3 and 4: "+lca);
	}

}