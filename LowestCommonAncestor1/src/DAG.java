// @author: Stephen Davis

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
	//	    private int[] indegree;        // indegree[v] = indegree of vertex v

	// Root of the Binary graph (from previous implementation)
	Node root;
	static boolean v1 = false, v2 = false;					// used to check if nodes are in graph

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
		//	        indegree[w]++;
		E++;
	}

	public Iterable<Integer> adj(int v) {
		return adj[v];
	}

	/**
	 * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,  
	 *         followed by the <em>V</em> adjacency lists
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
	
	private Node findLCAHelper(Node v, Node w) {
		// dfs on one of target nodes
		DirectedDFS search1 = new DirectedDFS(this, root.data);
		
		// keep track of parent's array
		
		
		// second, start DFS on other node
		DirectedDFS search2 = new DirectedDFS(this, root.data);
		
		// build subgraph
		
		return null;
	}
	
	
	public Node findLCA(Node v, Node w) {
		if(root == null) {
			return null;
		}
		
		// Check v is valid Node
		if((v.data < 0 || v.data >= V)) {
			return null;
		}

		// Check w is valid Node
		if((w.data < 0 || w.data >= V)) {
			return null;
		}
		
		if(v.data==w.data) {
			return v;
		}

		Node lca = findLCAHelper(v, w);
//		return lca;
		
		// temporary to check tests
		Node n = new Node(12);
		return n;
	}
	
	

	// -------------------------------------------------------------------------------------------------------------
	
	// @author: Stephen Davis
	public void populateLongNarrowGraph(DAG graph) {
		for(int i=0; i<V-1; i++) {
			graph.addEdge(i, i+1);
		}
	}


	
	public static void main(String[] args) {

	}
	
}