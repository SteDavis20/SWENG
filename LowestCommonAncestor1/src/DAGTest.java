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
	 * 			Errors (return null):
	 * 			v is not a node in graph
	 * 			w is not a node in graph
	 * 			v and w are not nodes in graph
	 * 			graph is empty
	 * 			
	 * 			return cases:
	 * 			root is LCA
	 * 			v is LCA
	 * 			w is LCA
	 * 			Some other parent node is LCA
	 * 
	 * */
	
	public static void main(String[] args) {
		
		// populating various different kinds of graphs
//		DAG emptygraph = new DAG();
//		
//		DAG graphLeaningLeft = new DAG();
//		graphLeaningLeft.populateLeftLeaninggraph(graphLeaningLeft);
//		
//		DAG graphLeaningRight = new DAG();
//		graphLeaningRight.populateRightLeaninggraph(graphLeaningRight);
//		
//		DAG balancedAndLonggraph = new DAG();
//		balancedAndLonggraph.populateBalancedAndLonggraph(balancedAndLonggraph);
//		
//		DAG shortgraph = new DAG();
//		shortgraph.populateShortgraph(shortgraph);
		
//		DAG longNarrowGraph = new DAG(8);
//		longNarrowGraph.populateLongNarrowGraph(longNarrowGraph);
	}
	
//	DAG emptygraph = new DAG();
	
	@Test
	public void testEmptyGraph() {
		DAG graph = new DAG(0);
		assertEquals("Checking LCA works for empty graph", -1, graph.findLCA((new Node(1)), (new Node(2))));
	}
	
	@Test
	public void testfindLCASomeParent() {
		DAG longNarrowGraph = new DAG(8);
		longNarrowGraph.populateLongNarrowGraph(longNarrowGraph);
		Node n1 = new Node(5);
		Node n2 = new Node(6);
		assertEquals("Checking LCA works where some parent is LCA in long graph", n1.data, longNarrowGraph.findLCA(n1, n2));

//		DAG balancedAndLonggraph = new DAG();
//		balancedAndLonggraph.populateBalancedAndLonggraph(balancedAndLonggraph);
//		Node v = new Node(1);
//		Node w = new Node(7);
//		assertEquals("Checking LCA in balanced graph where some parent is LCA", balancedAndLonggraph.root.leftChild, balancedAndLonggraph.findLCA(v.data, w.data));
//		
//		Node v2 = new Node(14);
//		Node w2 = new Node(15);
//		assertEquals("Checking LCA in balanced graph where some parent is LCA", balancedAndLonggraph.root.rightChild.rightChild, balancedAndLonggraph.findLCA(v2.data, w2.data));
//		
//		DAG longNarrowgraph = new DAG();
//		longNarrowgraph.populateLongNarrowgraph(longNarrowgraph);
//		Node l1 = new Node(9);
//		Node m1 = new Node(8);
//		assertEquals("Checking LCA in long, narrow graph where some parent is LCA", longNarrowgraph.root.leftChild.rightChild, longNarrowgraph.findLCA(l1.data, m1.data));
	}	
	
	@Test
	public void testFindLCARootIsLCA() {
		DAG longNarrowGraph = new DAG(8);
		longNarrowGraph.populateLongNarrowGraph(longNarrowGraph);
		Node n1 = new Node(0);
		Node n2 = new Node(6);
		assertEquals("Checking LCA works where root is LCA in long graph", n1.data, longNarrowGraph.findLCA(n1, n2));
	
//		DAG shortgraph = new DAG();
//		shortgraph.populateShortgraph(shortgraph);
//		Node s = new Node(10);
//		Node t = new Node(4);
//		assertEquals("Checking LCA in a short graph where root is the LCA", shortgraph.root, shortgraph.findLCA(s.data, t.data));
//
//		DAG balancedAndLonggraph = new DAG();
//		balancedAndLonggraph.populateBalancedAndLonggraph(balancedAndLonggraph);
//		Node v = new Node(9);
//		Node w = new Node(8);
//		assertEquals("Checking LCA in a balanced graph where root is the LCA", balancedAndLonggraph.root, balancedAndLonggraph.findLCA(v.data, w.data));
//		
//		DAG longNarrowgraph = new DAG();
//		longNarrowgraph.populateLongNarrowgraph(longNarrowgraph);
//		Node l1 = new Node(8);
//		Node m1 = new Node(10);
//		assertEquals("Checking LCA in a long, narrow graph where root is the LCA", longNarrowgraph.root, longNarrowgraph.findLCA(l1.data, m1.data));
	}
	
	@Test
	public void testfindLCAVIsAncestor() {
		DAG longNarrowGraph = new DAG(8);
		longNarrowGraph.populateLongNarrowGraph(longNarrowGraph);
		Node n1 = new Node(3);
		Node n2 = new Node(6);
		assertEquals("Checking LCA works where node1 is LCA in long graph", n1.data, longNarrowGraph.findLCA(n1, n2));
//		DAG graphLeaningRight = new DAG();
//		graphLeaningRight.populateRightLeaninggraph(graphLeaningRight);
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
//		balancedAndLonggraph.populateBalancedAndLonggraph(balancedAndLonggraph);
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
//		longNarrowgraph.populateLongNarrowgraph(longNarrowgraph);
//		Node l1 = new Node(4);
//		Node m1 = new Node(8);
//		assertEquals("Checking LCA in a long, narrow graph where V is the LCA", longNarrowgraph.root.leftChild, longNarrowgraph.findLCA(l1.data, m1.data));
	}
	
	@Test
	public void testfindLCAWIsAncestor() {
		DAG longNarrowGraph = new DAG(8);
		longNarrowGraph.populateLongNarrowGraph(longNarrowGraph);
		Node n1 = new Node(6);
		Node n2 = new Node(3);
		assertEquals("Checking LCA works where node2 is LCA in long graph", n2.data, longNarrowGraph.findLCA(n1, n2));
//		DAG graphLeaningRight = new DAG();
//		graphLeaningRight.populateRightLeaninggraph(graphLeaningRight);
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
//		balancedAndLonggraph.populateBalancedAndLonggraph(balancedAndLonggraph);
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
//		longNarrowgraph.populateLongNarrowgraph(longNarrowgraph);
//		Node l1 = new Node(8);
//		Node m1 = new Node(4);
//		assertEquals("Checking LCA in a long, narrow graph where W is the LCA", longNarrowgraph.root.leftChild, longNarrowgraph.findLCA(l1.data, m1.data));
	}
	
	@Test
	public void testfindLCAVAndWEqual() {
		DAG longNarrowGraph = new DAG(8);
		longNarrowGraph.populateLongNarrowGraph(longNarrowGraph);
		Node n1 = new Node(3);
		Node n2 = new Node(3);
		assertEquals("Checking LCA works where node1 and node2 are equal in long graph", n1.data, longNarrowGraph.findLCA(n1, n2));
//		DAG balancedAndLonggraph = new DAG();
//		balancedAndLonggraph.populateBalancedAndLonggraph(balancedAndLonggraph);
//		Node v = balancedAndLonggraph.root.leftChild.leftChild;
//		Node w = balancedAndLonggraph.root.leftChild.leftChild;
//		assertEquals("Checking LCA in a balanced graph where V and W are the same node", v, balancedAndLonggraph.findLCA(v.data, w.data));
//		
//		DAG longNarrowgraph = new DAG();
//		longNarrowgraph.populateLongNarrowgraph(longNarrowgraph);
//		Node l1 = new Node(4);
//		Node m1 = new Node(4);
//		assertEquals("Checking LCA in a long, narrow graph where V and W are equal", longNarrowgraph.root.leftChild, longNarrowgraph.findLCA(l1.data, m1.data));
	}
	
	@Test
	public void testfindLCAVNotAValidNode() {
		DAG longNarrowGraph = new DAG(8);
		longNarrowGraph.populateLongNarrowGraph(longNarrowGraph);
		Node n1 = new Node(10);
		Node n2 = new Node(3);
		assertEquals("Checking LCA works where node1 is an invalid node in long graph", -1, longNarrowGraph.findLCA(n1, n2));
//		DAG balancedAndLonggraph = new DAG();
//		balancedAndLonggraph.populateBalancedAndLonggraph(balancedAndLonggraph);
//		Node v = new Node(20);
//		Node w = new Node(8);
//		assertEquals("Checking LCA in a balanced graph where V (node1) is not a valid node", null, balancedAndLonggraph.findLCA(v.data, w.data));
//		
//		DAG longNarrowgraph = new DAG();
//		longNarrowgraph.populateLongNarrowgraph(longNarrowgraph);
//		Node l1 = new Node(11);
//		Node m1 = new Node(8);
//		assertEquals("Checking LCA in a long, narrow graph where V is not a valid node", null, longNarrowgraph.findLCA(l1.data, m1.data));
	}
	
	@Test
	public void testfindLCAWNotAValidNode() {
		DAG longNarrowGraph = new DAG(8);
		longNarrowGraph.populateLongNarrowGraph(longNarrowGraph);
		Node n1 = new Node(3);
		Node n2 = new Node(10);
		assertEquals("Checking LCA works where node2 is an invalid node in long graph", -1, longNarrowGraph.findLCA(n1, n2));
//		DAG balancedAndLonggraph = new DAG();
//		balancedAndLonggraph.populateBalancedAndLonggraph(balancedAndLonggraph);
//		Node w = new Node(20);
//		Node v = new Node(8);
//		assertEquals("Checking LCA in a balanced graph where W (node2) is not a valid node", null, balancedAndLonggraph.findLCA(v.data, w.data));
//		
//		DAG longNarrowgraph = new DAG();
//		longNarrowgraph.populateLongNarrowgraph(longNarrowgraph);
//		Node l1 = new Node(4);
//		Node m1 = new Node(11);
//		assertEquals("Checking LCA in a long, narrow graph where W is not a valid node", null, longNarrowgraph.findLCA(l1.data, m1.data));
	}
	
}
