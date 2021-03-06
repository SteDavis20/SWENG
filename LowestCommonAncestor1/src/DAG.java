// @author: Stephen Davis
// code is adapted from code provided by @Robert Sedgewick in his textbook Algorithms 4th Edition.

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

class Node {
	int data;

	Node(int value) {
		this.data = value;
	}
}

public class DAG
{

	private static final String NEWLINE = System.getProperty("line.separator");

	private final int V;           // number of vertices in graph DAG
	private int E;                 // number of edges in graph DAG
	private ArrayList<Integer>[] adj;    // adj[v] = adjacency list for vertex v

	private boolean[] marked;  	   // marked[v] = true iff v is reachable from source(s)

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
		adj = (ArrayList<Integer>[]) new ArrayList[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new ArrayList<Integer>();
		}
		if(V>0) {
			root = new Node(0);
		}
	}

	public int V() {
		return V;
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

	public ArrayList<Integer> BFS(int s) {
		ArrayList<Integer> path = new ArrayList<Integer>();
		marked = new boolean[this.V()];
		path = bfs(path, s);
		return path;
	}

	private ArrayList<Integer> bfs(ArrayList<Integer> path, int s) {
		LinkedList<Integer> queue = new LinkedList<Integer>();
		marked[s] = true;
		queue.add(s);
		while (!queue.isEmpty()) {
			s = queue.poll();					// dequeue a vertex from queue
			path.add(s);
			for (int w : this.adj(s)) {
				if (!marked[w]) {
					marked[w] = true;
					queue.add(w);
				}
			}
		}
		return path;	
	}

	/* My Altered Approach to the following Source: https://www.baeldung.com/cs/lowest-common-ancestor-acyclic-graph
	 * 
	 * Variable names should explain the code sufficiently.
	 * 
	 * Note: we first reverse the graph so we can easily access a given node's ancestors via depth-first search.
	 */
	private int findLCAHelper(int start, Node v, Node w) {
		DAG reversedGraph = this.reverse();

		ArrayList<Integer> vPath = reversedGraph.BFS(v.data);
		ArrayList<Integer> wPath = reversedGraph.BFS(w.data);
		ArrayList<Integer> commonAncestors = new ArrayList<Integer>();

		for(Integer ancestorOfV : vPath) {
			for(Integer ancestorOfW : wPath) {
				if(ancestorOfV == ancestorOfW) {
					commonAncestors.add(ancestorOfV);	
				}
			}
		}

		int lca = -1;

		int mostDepth = -1;
		int ancestorDepth;

		for(Integer ancestor : commonAncestors) {
			ancestorDepth = getVertexDepth(start, ancestor);
			if(ancestorDepth > mostDepth) {
				mostDepth = ancestorDepth;
				lca = ancestor;
			}
		}
		return lca;
	}

	public int getVertexDepth(int start, int destination) {
		Stack<Integer> visited = new Stack<Integer>();
		int depth = -1;
		depth = getVertexDepthRecursive(start, destination, visited, depth);
		return depth;
	}

	private int getVertexDepthRecursive(int currentVertex, int destinationVertex, Stack<Integer> visited, int depth) {
		visited.push(currentVertex);
		if(currentVertex == destinationVertex) {
			depth = visited.size()-1;
		}
		for(Integer i : this.adj(currentVertex)) {
			if (!visited.contains(i)) {
				depth = getVertexDepthRecursive(i, destinationVertex, visited, depth);
				if (!visited.empty()) {
					visited.pop();
				}
			}
		}
		return depth;
	}

	public int findLCA(int root, Node v, Node w) {
		if(this.root == null) {
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
		
		int lca = findLCAHelper(root, v, w);
		return lca;
	}

	/* Tester */
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
				int lca = graph.findLCA(0, n1, n2);
				System.out.println("Expecting LCA of 3 and 4 to be 1... ");
				System.out.println("LCA of 3 and 4: "+lca);
	}

}