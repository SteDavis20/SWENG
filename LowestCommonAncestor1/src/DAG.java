// @author: Stephen Davis

// adapted from Dhruv's work (from GeeksForGeeks).

// Java implementation to find lowest common ancestor of
// n1 and n2 using one traversal of binary graph
// It also handles cases even when n1 and n2 are not there in graph

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
	        
	    /**
	     * Returns the number of vertices in this DAG.
	     *
	     * @return the number of vertices in this DAG
	     */
	    public int V() {
	        return V;
	    }

	    /**
	     * Returns the number of edges in this DAG.
	     *
	     * @return the number of edges in this DAG
	     */
	    public int E() {
	        return E;
	    }

	    /**
	     * Adds the directed edge v -> w to this DAG.
	     *
	     * @param  v the tail vertex
	     * @param  w the head vertex
	     * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
	     */
	    public void addEdge(int v, int w) {
	        adj[v].add(w);
//	        indegree[w]++;
	        E++;
	    }

	    /**
	     * Returns the vertices adjacent from vertex {@code v} in this DAG.
	     *
	     * @param  v the vertex
	     * @return the vertices adjacent from vertex {@code v} in this DAG, as an iterable
	     * @throws IllegalArgumentException unless {@code 0 <= v < V}
	     */
	    public Iterable<Integer> adj(int v) {
	        return adj[v];
	    }

	    /**
	     * Returns a string representation of the graph.
	     *
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

	    public static void main(String[] args) {
	        
	    }

	// This function returns pointer to LCA of two given
	// values n1 and n2.
	// v1 is set as true by this function if n1 is found
	// v2 is set as true by this function if n2 is found
	Node findLCAUtil(Node node, int n1, int n2)
	{
		// Base case
		if (node == null)
			return null;

		//Store result in temp, in case of key match so that we can search for other key also.
		Node temp=null;

		// If either n1 or n2 matches with root's key, report the presence
		// by setting v1 or v2 as true and return root (Note that if a key
		// is ancestor of other, then the ancestor key becomes LCA)
		if (node.data == n1)
		{
			v1 = true;
			temp = node;
		}
		if (node.data == n2)
		{
			v2 = true;
			temp = node;
		}

		// Look for keys in left and right subgraphs
		Node left_lca = findLCAUtil(node.leftChild, n1, n2);
		Node right_lca = findLCAUtil(node.rightChild, n1, n2);

		if (temp != null)
			return temp;

		// If both of the above calls return Non-NULL, then one key
		// is present in one subgraph and other is present in other,
		// So this node is the LCA
		if (left_lca != null && right_lca != null)
			return node;

		// Otherwise check if left subgraph or right subgraph is LCA
		return (left_lca != null) ? left_lca : right_lca;
	}

	// Finds lca of n1 and n2 under the subgraph rooted with 'node'
	Node findLCA(int n1, int n2)
	{
		// Initialize n1 and n2 as not visited
		v1 = false;
		v2 = false;

		// Find lca of n1 and n2 using the technique discussed above
		Node lca = findLCAUtil(root, n1, n2);

		// Return LCA only if both n1 and n2 are present in graph
		if (v1 && v2)
			return lca;

		// Else return NULL
		return null;
	}

	// @author: Stephen Davis
	public boolean checkIsNode(Node root, Node n) {
		if(root==null) {
			return false;
		}
		if(root.data==n.data) {
			return true;
		}
		if(checkIsNode(root.leftChild, n)) {
			return true;
		}
		else if(checkIsNode(root.rightChild, n)) {
			return true;
		}
		return false;
	}
	
	// @author: Stephen Davis
//	// Below 4 methods are used to populate various different kinds of graphs for unit testing
//	public void populateLeftLeaninggraph(DAG graph) {
//		graph.root = new Node(6);
//		graph.root.leftChild = new Node(4);
//		graph.root.leftChild.leftChild = new Node(10);
//		graph.root.leftChild.leftChild.leftChild = new Node(3);
//		graph.root.leftChild.leftChild.leftChild.leftChild = new Node(5);
//	}
//	
//	// @author: Stephen Davis
//	public void populateRightLeaninggraph(DAG graph) {
//		graph.root = new Node(6);
//		graph.root.rightChild = new Node(4);
//		graph.root.rightChild.rightChild = new Node(10);
//		graph.root.rightChild.rightChild.rightChild = new Node(3);
//		graph.root.rightChild.rightChild.rightChild.rightChild = new Node(5);
//	}
//	
//	// @author: Stephen Davis
//	public void populateBalancedAndLonggraph(DAG graph) {
//		graph.root = new Node(6);
//		graph.root.leftChild = new Node(4);
//		graph.root.rightChild = new Node(10);
//		graph.root.leftChild.leftChild = new Node(3);
//		graph.root.leftChild.rightChild = new Node(5);
//		graph.root.rightChild.leftChild = new Node(12);
//		graph.root.rightChild.rightChild = new Node(11);
//		graph.root.leftChild.rightChild.rightChild = new Node(2);
//		graph.root.leftChild.rightChild.leftChild = new Node(7);
//		graph.root.leftChild.leftChild.leftChild = new Node(1);
//		graph.root.leftChild.leftChild.rightChild = new Node(9);
//		graph.root.rightChild.leftChild.leftChild = new Node(8);
//		graph.root.rightChild.leftChild.rightChild = new Node(13);
//		graph.root.rightChild.rightChild.leftChild = new Node(14);
//		graph.root.rightChild.rightChild.rightChild = new Node(15);
//	}
//	
//	// @author: Stephen Davis
//	public void populateShortgraph(DAG graph) {
//		graph.root = new Node(6);
//		graph.root.leftChild = new Node(4);
//		graph.root.rightChild = new Node(10);
//	}
//	
	// @author: Stephen Davis
		public void populateLongNarrowGraph(DAG graph) {
			for(int i=0; i<V; i++) {
				graph.addEdge(i, i+1);
			}
		}

}