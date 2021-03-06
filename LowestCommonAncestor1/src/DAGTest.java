// @ author: Stephen Davis
import static org.junit.Assert.*;

import org.junit.Test;
import java.util.ArrayList;

public class DAGTest {

	/* By wikipedia definition, the lowest common ancestor (LCA) of two nodes v and w in a graph or directed 
	 * acyclic graph (DAG) T is the lowest (i.e. deepest) node that has both v and w as descendants, where
	 * we define each node to be a descendant of itself.
	 * The important thing to note is that a node can be an ancestor of itself. i.e., if v has a direct
	 * connection from w, w is the lowest common ancestor. 
	 * Cases:
	 * 			Errors (return -1):
	 * 				v is not a node in graph
	 * 				w is not a node in graph
	 * 				v and w are not nodes in graph
	 * 				graph is empty
	 * 				v and w have no common ancestor (disconnected subgraphs)
	 * 			
	 * 			return cases:
	 * 				root is LCA
	 * 				v is LCA
	 * 				w is LCA
	 * 				Some other parent node is LCA
	 * 
	 * */

	DAG emptyGraph = new DAG(0);
	DAG singleNodeGraph = new DAG(1);
	DAG shortGraph = createShortGraph();	
	DAG longNarrowGraph = createLongNarrowGraph();
	DAG balancedTree = createBalancedTree();
	DAG isolatedVertexGraph = createIsolatedVertexGraph();
	DAG disjointSubGraphs = createDisjointSubGraphs();
	DAG noEdgesGraph = new DAG(5);
	DAG multipleParentsGraph = createMultipleParentsGraph();

	/*
						0
					 /	   \
				   1		 2	
	 */
	public DAG createShortGraph() {
		DAG graph = new DAG(3);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		return graph;
	}

	/*
						0
					  /	   \
	  		    	1	     6
				 /     \	   \				3
			  4   		 5		 2
		    / 	\	 			   				 
	      7       8  				
	 */
	public DAG createIsolatedVertexGraph() {
		DAG graph = new DAG(9);
		graph.addEdge(0, 1);
		graph.addEdge(1, 4);
		graph.addEdge(4, 7);
		graph.addEdge(4, 8);
		graph.addEdge(1, 5);
		graph.addEdge(0, 6);
		graph.addEdge(6, 2);
		return graph;
	}	

	/*
					0
				 /	  \
			   1	    2		4 --> 5 
			    \		
				 3	    						6 --> 7 --> 8 	
	 */
	public DAG createDisjointSubGraphs() {
		DAG graph = new DAG(9);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 3);

		graph.addEdge(4, 5);

		graph.addEdge(6, 7);
		graph.addEdge(7, 8);
		return graph;
	}

	//		0 --> 1 --> 2 --> 3 --> 4 --> 5 --> 6 --> 7
	public DAG createLongNarrowGraph() {
		DAG graph = new DAG(8);
		for(int i=0; i<graph.V()-1; i++) {
			graph.addEdge(i, i+1);
		}
		return graph;
	}

	/*
							0
						/		\
					  1		      6
				   /    \		/
				  4       5	  2
				 / \	 /
			   7    8  3 
	 */
	public DAG createBalancedTree() {
		DAG graph = new DAG(9);
		graph.addEdge(0, 1);
		graph.addEdge(1, 4);
		graph.addEdge(4, 7);
		graph.addEdge(4, 8);
		graph.addEdge(1, 5);
		graph.addEdge(5, 3);
		graph.addEdge(0, 6);
		graph.addEdge(6, 2);
		return graph;
	}

	/*
						0
  					/	   \
				 1	     	 6
			 /      \     	 | \				
		   4   	       5	 |   2
		 / 	 \	 	/    \	 |	   				 
				  /		   \ |
	   7       8   ---->     3  				
	 */
	public DAG createMultipleParentsGraph() {
		DAG graph = new DAG(9);
		graph.addEdge(0, 1);
		graph.addEdge(1, 4);
		graph.addEdge(4, 7);
		graph.addEdge(4, 8);
		graph.addEdge(1, 5);
		graph.addEdge(0, 6);
		graph.addEdge(6, 2);
		graph.addEdge(6, 3);
		graph.addEdge(5, 3);
		graph.addEdge(8, 3);
		graph.addEdge(5, 8);
		return graph;
	}		

	/*  Graphs for Testing:
  								emptyGraph 

 								singleNodeGraph

								shortGraph	

 								longNarrowGraph
 								
 								balancedTree

								isolatedVertexGraph

								disjointSubGraphs

								noEdgesGraph

								multipleParentsGraph
	 */

	@Test
	public void testEmptyGraph() {
		assertEquals("Checking LCA works for empty graph", -1, emptyGraph.findLCA(0, (new Node(1)), (new Node(2))));
	}

	@Test
	public void testSingleNodeGraph() {
		assertEquals("Checking LCA works for single node graph", -1, singleNodeGraph.findLCA(0, (new Node(1)), (new Node(2))));
	}

	@Test
	public void testfindLCASomeParent() {		
		Node n3 = new Node(3);
		Node n4 = new Node(2);
		assertEquals("Checking LCA works where some parent is LCA in disjoint sub graphs", 0, disjointSubGraphs.findLCA(0, n3, n4));

		Node n1 = new Node(5);
		Node n2 = new Node(7);
		assertEquals("Checking LCA works where some parent is LCA in a graph with an isolated Vertex", 1, isolatedVertexGraph.findLCA(0, n1, n2));

		Node n5 = new Node(8);

		assertEquals("Checking LCA works where some parent is LCA in a graph with multiple parents for one Vertex", 4, multipleParentsGraph.findLCA(0, n3, n2));
		assertEquals("Checking LCA works where some parent is LCA in a graph with multiple parents for one Vertex", 4, multipleParentsGraph.findLCA(0, n5, n2));
		
		assertEquals("Checking LCA works where some parent is LCA in balanced tree", 1, balancedTree.findLCA(0, n2, n3));
		assertEquals("Checking LCA works where some parent is LCA in balanced tree", 1, balancedTree.findLCA(0, n2, n1));
	}	

	@Test
	public void testFindLCARootIsLCA() {
		Node n1 = new Node(0);
		Node n2 = new Node(7);
		assertEquals("Checking LCA works where root is LCA in long graph", 0, longNarrowGraph.findLCA(0, n1, n2));
		
		Node n3 = new Node(3);
		Node n4 = new Node(2);
		assertEquals("Checking LCA works where root is LCA in disjoint sub graphs", 0, disjointSubGraphs.findLCA(0, n3, n4));

		Node n6 = new Node(1);
		assertEquals("Checking LCA works where root is LCA in short graph", 0, shortGraph.findLCA(0, n6, n4));
		
		assertEquals("Checking LCA works where root is LCA in isolated vertex graph", 0, isolatedVertexGraph.findLCA(0, n2, n4));

		Node n5 = new Node(8);
		assertEquals("Checking LCA works where root is LCA in a graph with multiple parents for one Vertex", 0, multipleParentsGraph.findLCA(0, n4, n5));
		
		assertEquals("Checking LCA works where root is LCA in balanced tree", 0, balancedTree.findLCA(0, n5, n4));
	}

	@Test
	public void testfindLCAVIsAncestor() {
		Node n1 = new Node(3);
		Node n2 = new Node(7);
		assertEquals("Checking LCA works where node1 is LCA in long graph", n1.data, longNarrowGraph.findLCA(0, n1, n2));

		Node n3 = new Node(1);
		assertEquals("Checking LCA works where node1 is LCA in disjoint sub graphs", n3.data, disjointSubGraphs.findLCA(0, n3, n1));

		Node n4 = new Node(8);		
		assertEquals("Checking LCA works where node1 is LCA in isolated vertex graph", n3.data, isolatedVertexGraph.findLCA(0, n3, n4));

		Node n5 = new Node(4);
		assertEquals("Checking LCA works where node1 is LCA in a graph with multiple parents for one Vertex", n5.data, multipleParentsGraph.findLCA(0, n5, n1));
		
		assertEquals("Checking LCA works where node1 is LCA in balanced tree", n5.data, balancedTree.findLCA(0, n5, n2));		
	}

	@Test
	public void testfindLCAWIsAncestor() {
		Node n1 = new Node(7);
		Node n2 = new Node(3);
		assertEquals("Checking LCA works where node2 is LCA in long graph", n2.data, longNarrowGraph.findLCA(0, n1, n2));

		Node n3 = new Node(1);
		assertEquals("Checking LCA works where node2 is LCA in disjoint sub graphs", n3.data, disjointSubGraphs.findLCA(0, n2, n3));

		Node n4 = new Node(8);		
		assertEquals("Checking LCA works where node2 is LCA in isolated vertex graph", n3.data, isolatedVertexGraph.findLCA(0, n4, n3));

		assertEquals("Checking LCA works where node2 is LCA in a graph with multiple parents for one Vertex", n3.data, multipleParentsGraph.findLCA(0, n2, n3));
		
		assertEquals("Checking LCA works where node2 is LCA in balanced tree", n3.data, balancedTree.findLCA(0, n4, n3));
	}

	@Test
	public void testfindLCAVAndWEqual() {
		Node n1 = new Node(3);
		Node n2 = new Node(3);
		assertEquals("Checking LCA works where node1 and node2 are equal in long graph", n1.data, longNarrowGraph.findLCA(0, n1, n2));

		assertEquals("Checking LCA works where node1 and node2 are equal in disjoint sub graphs", n1.data, disjointSubGraphs.findLCA(0, n1, n2));

		assertEquals("Checking LCA works where node1 and node2 are equal in isolated vertex graph", n1.data, isolatedVertexGraph.findLCA(0, n1, n2));

		assertEquals("Checking LCA works where node1 and node2 are equal in a graph with multiple parents for one Vertex", n1.data, multipleParentsGraph.findLCA(0, n1, n2));

		assertEquals("Checking LCA works where node1 and node2 are equal in balanced tree", n1.data, balancedTree.findLCA(0, n1, n2));
		
		assertEquals("Checking LCA works where node1 and node2 are equal in graph with no edges", n1.data, noEdgesGraph.findLCA(0, n1, n2));
	}

	@Test
	public void testfindLCAVNotAValidNode() {
		Node n1 = new Node(10);
		Node n2 = new Node(3);
		assertEquals("Checking LCA works where node1 is an invalid node in long graph", -1, longNarrowGraph.findLCA(0, n1, n2));

		assertEquals("Checking LCA works where node1 is an invalid node in disjoint sub graphs", -1, disjointSubGraphs.findLCA(0, n1, n2));

		assertEquals("Checking LCA works where node1 is an invalid node in isolated vertex graph", -1, isolatedVertexGraph.findLCA(0, n1, n2));

		assertEquals("Checking LCA works where node1 is an invalid node in a graph with multiple parents for one Vertex", -1, multipleParentsGraph.findLCA(0, n1, n2));
		
		assertEquals("Checking LCA works where node1 is an invalid node in balanced tree", -1, balancedTree.findLCA(0, n1, n2));
		
		assertEquals("Checking LCA works where node1 is an invalid node in graph with no edges", -1, noEdgesGraph.findLCA(0, n1, n2));
	}

	@Test
	public void testfindLCAWNotAValidNode() {
		Node n1 = new Node(3);
		Node n2 = new Node(10);
		assertEquals("Checking LCA works where node2 is an invalid node in long graph", -1, longNarrowGraph.findLCA(0, n1, n2));

		assertEquals("Checking LCA works where node2 is an invalid node in disjoint sub graphs", -1, disjointSubGraphs.findLCA(0, n1, n2));

		assertEquals("Checking LCA works where node2 is an invalid node in isolated vertex graph", -1, isolatedVertexGraph.findLCA(0, n1, n2));

		assertEquals("Checking LCA works where node2 is an invalid node in a graph with multiple parents for one Vertex", -1, multipleParentsGraph.findLCA(0, n1, n2));
		
		assertEquals("Checking LCA works where node2 is an invalid node in balanced tree", -1, balancedTree.findLCA(0, n1, n2));
		
		assertEquals("Checking LCA works where node2 is an invalid node in graph with no edges", -1, noEdgesGraph.findLCA(0, n1, n2));
	}

	@Test
	public void testNoLCA() {
		Node n1 = new Node(4);
		Node n2 = new Node(7);
		Node n3 = new Node(3);
		Node n4 = new Node(5);
		Node n5 = new Node(2);
		assertEquals("Checking LCA works where input nodes are valid but no LCA in disjoint sub graphs", -1, disjointSubGraphs.findLCA(0, n1, n2));
		assertEquals("Checking LCA works where input nodes are valid but no LCA in disjoint sub graphs", -1, disjointSubGraphs.findLCA(0, n3, n4));
		
		assertEquals("Checking LCA works where input nodes are valid but no LCA in isolated vertex graph", -1, isolatedVertexGraph.findLCA(0, n3, n2));
		
		assertEquals("Checking LCA works where input nodes are valid but no LCA in graph with no edges", -1, noEdgesGraph.findLCA(0, n1, n3));		
		assertEquals("Checking LCA works where input nodes are valid but no LCA in graph with no edges", -1, noEdgesGraph.findLCA(0, n1, n5));

	}

}
