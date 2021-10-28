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

	//has path from every vertex to every other vertex
//	DAG connectedGraph = createConnectedGraph(connectedGraph);

	DAG isolatedVertexGraph = createIsolatedVertexGraph();

	// should not work for lca
	DAG cycleGraph = createCycleGraph();

	DAG disjointSubGraphs = createDisjointSubGraphs();

	DAG noEdgesGraph = new DAG(5);

//	public static void main(String[] args) {
//
//	}

	//	0
	//				 /	   \
	//			   1		 2	
	public DAG createShortGraph() {
		DAG graph = new DAG(3);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		return graph;
	}

	//					0
	//				  /	   \
	//  		    1	     6
	//			 /     \	   \				3
	//		  4   		 5		 2
	//	    / 	\	 			   				 
	//    7       8  				
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

	//						     0
	//						 /		  \								  
	//					   1	<--> 	2
	public DAG createCycleGraph() {
		DAG graph = new DAG(3);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 2);
		graph.addEdge(2, 1);
		return graph;
	}

	//				0
	//			 /	  \
	//		   1	    2		4 --> 5 
	//		    \		
	//			 3	    						6 --> 7 --> 8 	
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

	//						0
	//					/		\
	//				  1		      6
	//			   /    \		/
	//			  4       5	  2
	//			 / \	 /
	//		   7    8  3 
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



	@Test
	public void testEmptyGraph() {
		DAG graph = new DAG(0);
		assertEquals("Checking LCA works for empty graph", -1, graph.findLCA((new Node(1)), (new Node(2))));
	}

	@Test
	public void testfindLCASomeParent() {
		Node n1 = new Node(5);
		Node n2 = new Node(6);
		assertEquals("Checking LCA works where some parent is LCA in long graph", n1.data, longNarrowGraph.findLCA(n1, n2));

		//		DAG balancedAndLonggraph = new DAG();
		//		balancedAndLonggraph.createBalancedAndLonggraph(balancedAndLonggraph);
		//		Node v = new Node(1);
		//		Node w = new Node(7);
		//		assertEquals("Checking LCA in balanced graph where some parent is LCA", balancedAndLonggraph.root.leftChild, balancedAndLonggraph.findLCA(v.data, w.data));
		//		
		//		Node v2 = new Node(14);
		//		Node w2 = new Node(15);
		//		assertEquals("Checking LCA in balanced graph where some parent is LCA", balancedAndLonggraph.root.rightChild.rightChild, balancedAndLonggraph.findLCA(v2.data, w2.data));
		//		
		//		DAG longNarrowgraph = new DAG();
		//		longNarrowgraph.createLongNarrowgraph(longNarrowgraph);
		//		Node l1 = new Node(9);
		//		Node m1 = new Node(8);
		//		assertEquals("Checking LCA in long, narrow graph where some parent is LCA", longNarrowgraph.root.leftChild.rightChild, longNarrowgraph.findLCA(l1.data, m1.data));
	}	

	@Test
	public void testFindLCARootIsLCA() {
		Node n1 = new Node(0);
		Node n2 = new Node(6);
		assertEquals("Checking LCA works where root is LCA in long graph", n1.data, longNarrowGraph.findLCA(n1, n2));

		//		DAG shortgraph = new DAG();
		//		shortgraph.createShortgraph(shortgraph);
		//		Node s = new Node(10);
		//		Node t = new Node(4);
		//		assertEquals("Checking LCA in a short graph where root is the LCA", shortgraph.root, shortgraph.findLCA(s.data, t.data));
		//
		//		DAG balancedAndLonggraph = new DAG();
		//		balancedAndLonggraph.createBalancedAndLonggraph(balancedAndLonggraph);
		//		Node v = new Node(9);
		//		Node w = new Node(8);
		//		assertEquals("Checking LCA in a balanced graph where root is the LCA", balancedAndLonggraph.root, balancedAndLonggraph.findLCA(v.data, w.data));
		//		
		//		DAG longNarrowgraph = new DAG();
		//		longNarrowgraph.createLongNarrowgraph(longNarrowgraph);
		//		Node l1 = new Node(8);
		//		Node m1 = new Node(10);
		//		assertEquals("Checking LCA in a long, narrow graph where root is the LCA", longNarrowgraph.root, longNarrowgraph.findLCA(l1.data, m1.data));
	}

	@Test
	public void testfindLCAVIsAncestor() {
		Node n1 = new Node(3);
		Node n2 = new Node(6);
		assertEquals("Checking LCA works where node1 is LCA in long graph", n1.data, longNarrowGraph.findLCA(n1, n2));
		//		DAG graphLeaningRight = new DAG();
		//		graphLeaningRight.createRightLeaninggraph(graphLeaningRight);
		//		Node v = graphLeaningRight.root;
		//		Node w = null;
		//		if(graphLeaningRight.root.leftChild!=null) {
		//			w = graphLeaningRight.root.leftChild;
		//		}
		//		else if(graphLeaningRight.root.rightChild!=null) {
		//			w = graphLeaningRight.root.rightChild;
		//		}
		//		assertEquals("Checking LCA in a right leaning graph where V (node1) is the LCA", v, graphLeaningRight.findLCA(v.data, w.data));
		//		
		//		DAG balancedAndLonggraph = new DAG();
		//		balancedAndLonggraph.createBalancedAndLonggraph(balancedAndLonggraph);
		//		Node v2 = balancedAndLonggraph.root;
		//		Node w2 = null;
		//		if(balancedAndLonggraph.root.leftChild!=null) {
		//			w2 = balancedAndLonggraph.root.leftChild;
		//		}
		//		else if(balancedAndLonggraph.root.rightChild!=null) {
		//			w2 = balancedAndLonggraph.root.rightChild;
		//		}
		//		assertEquals("Checking LCA in a balanced graph where V (node1) is the LCA", v2, balancedAndLonggraph.findLCA(v2.data, w2.data));
		//		
		//		DAG longNarrowgraph = new DAG();
		//		longNarrowgraph.createLongNarrowgraph(longNarrowgraph);
		//		Node l1 = new Node(4);
		//		Node m1 = new Node(8);
		//		assertEquals("Checking LCA in a long, narrow graph where V is the LCA", longNarrowgraph.root.leftChild, longNarrowgraph.findLCA(l1.data, m1.data));
	}

	@Test
	public void testfindLCAWIsAncestor() {
		Node n1 = new Node(6);
		Node n2 = new Node(3);
		assertEquals("Checking LCA works where node2 is LCA in long graph", n2.data, longNarrowGraph.findLCA(n1, n2));
		//		DAG graphLeaningRight = new DAG();
		//		graphLeaningRight.createRightLeaninggraph(graphLeaningRight);
		//		Node w = graphLeaningRight.root;
		//		Node v = null;
		//		if(graphLeaningRight.root.leftChild!=null) {
		//			v = graphLeaningRight.root.leftChild;
		//		}
		//		else if(graphLeaningRight.root.rightChild!=null) {
		//			v = graphLeaningRight.root.rightChild;
		//		}
		//		assertEquals("Checking LCA in a right leaning graph where W (node2) is the LCA", w, graphLeaningRight.findLCA(v.data, w.data));
		//		
		//		DAG balancedAndLonggraph = new DAG();
		//		balancedAndLonggraph.createBalancedAndLonggraph(balancedAndLonggraph);
		//		Node w2 = balancedAndLonggraph.root;
		//		Node v2 = null;
		//		if(balancedAndLonggraph.root.leftChild!=null) {
		//			v2 = balancedAndLonggraph.root.leftChild;
		//		}
		//		else if(balancedAndLonggraph.root.rightChild!=null) {
		//			v2 = balancedAndLonggraph.root.rightChild;
		//		}
		//		assertEquals("Checking LCA in a balanced graph where W (node2) is the LCA", w2, balancedAndLonggraph.findLCA(v2.data, w2.data));
		//		
		//		DAG longNarrowgraph = new DAG();
		//		longNarrowgraph.createLongNarrowgraph(longNarrowgraph);
		//		Node l1 = new Node(8);
		//		Node m1 = new Node(4);
		//		assertEquals("Checking LCA in a long, narrow graph where W is the LCA", longNarrowgraph.root.leftChild, longNarrowgraph.findLCA(l1.data, m1.data));
	}

	@Test
	public void testfindLCAVAndWEqual() {
		Node n1 = new Node(3);
		Node n2 = new Node(3);
		assertEquals("Checking LCA works where node1 and node2 are equal in long graph", n1.data, longNarrowGraph.findLCA(n1, n2));
		//		DAG balancedAndLonggraph = new DAG();
		//		balancedAndLonggraph.createBalancedAndLonggraph(balancedAndLonggraph);
		//		Node v = balancedAndLonggraph.root.leftChild.leftChild;
		//		Node w = balancedAndLonggraph.root.leftChild.leftChild;
		//		assertEquals("Checking LCA in a balanced graph where V and W are the same node", v, balancedAndLonggraph.findLCA(v.data, w.data));
		//		
		//		DAG longNarrowgraph = new DAG();
		//		longNarrowgraph.createLongNarrowgraph(longNarrowgraph);
		//		Node l1 = new Node(4);
		//		Node m1 = new Node(4);
		//		assertEquals("Checking LCA in a long, narrow graph where V and W are equal", longNarrowgraph.root.leftChild, longNarrowgraph.findLCA(l1.data, m1.data));
	}

	@Test
	public void testfindLCAVNotAValidNode() {
		Node n1 = new Node(10);
		Node n2 = new Node(3);
		assertEquals("Checking LCA works where node1 is an invalid node in long graph", -1, longNarrowGraph.findLCA(n1, n2));
		//		DAG balancedAndLonggraph = new DAG();
		//		balancedAndLonggraph.createBalancedAndLonggraph(balancedAndLonggraph);
		//		Node v = new Node(20);
		//		Node w = new Node(8);
		//		assertEquals("Checking LCA in a balanced graph where V (node1) is not a valid node", null, balancedAndLonggraph.findLCA(v.data, w.data));
		//		
		//		DAG longNarrowgraph = new DAG();
		//		longNarrowgraph.createLongNarrowgraph(longNarrowgraph);
		//		Node l1 = new Node(11);
		//		Node m1 = new Node(8);
		//		assertEquals("Checking LCA in a long, narrow graph where V is not a valid node", null, longNarrowgraph.findLCA(l1.data, m1.data));
	}

	@Test
	public void testfindLCAWNotAValidNode() {
		Node n1 = new Node(3);
		Node n2 = new Node(10);
		assertEquals("Checking LCA works where node2 is an invalid node in long graph", -1, longNarrowGraph.findLCA(n1, n2));
		//		DAG balancedAndLonggraph = new DAG();
		//		balancedAndLonggraph.createBalancedAndLonggraph(balancedAndLonggraph);
		//		Node w = new Node(20);
		//		Node v = new Node(8);
		//		assertEquals("Checking LCA in a balanced graph where W (node2) is not a valid node", null, balancedAndLonggraph.findLCA(v.data, w.data));
		//		
		//		DAG longNarrowgraph = new DAG();
		//		longNarrowgraph.createLongNarrowgraph(longNarrowgraph);
		//		Node l1 = new Node(4);
		//		Node m1 = new Node(11);
		//		assertEquals("Checking LCA in a long, narrow graph where W is not a valid node", null, longNarrowgraph.findLCA(l1.data, m1.data));
	}

}
