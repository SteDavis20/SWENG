// @ author: Stephen Davis
import static org.junit.Assert.*;

import org.junit.Test;
import java.util.ArrayList;

public class LowestCommonAncestor1Test {

	/* By wikipedia definition, the lowest common ancestor (LCA) of two nodes v and w in a tree or directed 
	 * acyclic graph (DAG) T is the lowest (i.e. deepest) node that has both v and w as descendants, where
	 * we define each node to be a descendant of itself.
	 * The important thing to note is that a node can be an ancestor of itself. i.e., if v has a direct
	 * connection from w, w is the lowest common ancestor. 
	 * Cases:
	 * 			Errors (return null):
	 * 			v is not a node in tree
	 * 			w is not a node in tree
	 * 			v and w are not nodes in tree
	 * 			tree is empty
	 * 			
	 * 			return cases:
	 * 			root is LCA
	 * 			v is LCA
	 * 			w is LCA
	 * 			Some other parent node is LCA
	 * 
	 * */
	
	public static void main(String[] args) {
		
		// populating various different kinds of trees
//		BinaryTree emptyTree = new BinaryTree();
//		
//		BinaryTree treeLeaningLeft = new BinaryTree();
//		treeLeaningLeft.populateLeftLeaningTree(treeLeaningLeft);
//		
//		BinaryTree treeLeaningRight = new BinaryTree();
//		treeLeaningRight.populateRightLeaningTree(treeLeaningRight);
//		
//		BinaryTree balancedAndLongTree = new BinaryTree();
//		balancedAndLongTree.populateBalancedAndLongTree(balancedAndLongTree);
//		
//		BinaryTree shortTree = new BinaryTree();
//		shortTree.populateShortTree(shortTree);
		
//		BinaryTree longNarrowTree = new BinaryTree();
//		longNarrowTree.populateLongNarrowTree(longNarrowTree);
	}
	
	@Test
	public void testCheckIsNode() {
		DAG treeLeaningLeft = new DAG();
		treeLeaningLeft.populateLeftLeaningTree(treeLeaningLeft);
		Node n = new Node(5);
		Node n2 = new Node(16);
		assertEquals("Checking if valid node appears in left leaning tree", true, treeLeaningLeft.checkIsNode(treeLeaningLeft.root, n));
		assertEquals("Checking if invalid node appears in left leaning tree", false, treeLeaningLeft.checkIsNode(treeLeaningLeft.root, n2));
		
		DAG balancedAndLongTree = new DAG();
		balancedAndLongTree.populateBalancedAndLongTree(balancedAndLongTree);
		assertEquals("Checking if valid node appears in balanced tree", true, balancedAndLongTree.checkIsNode(balancedAndLongTree.root, n));
		assertEquals("Checking if valid node appears in balanced tree", false, balancedAndLongTree.checkIsNode(balancedAndLongTree.root, n2));
	}
	
	@Test
	public void testEmptyTree() {
		DAG emptyTree = new DAG();
		assertEquals("Checking if empty tree returns null", null, emptyTree.findLCA(3, 4));
	}
	
	@Test
	public void testfindLCASomeParent() {
		DAG balancedAndLongTree = new DAG();
		balancedAndLongTree.populateBalancedAndLongTree(balancedAndLongTree);
		Node v = new Node(1);
		Node w = new Node(7);
		assertEquals("Checking LCA in balanced tree where some parent is LCA", balancedAndLongTree.root.leftChild, balancedAndLongTree.findLCA(v.data, w.data));
		
		Node v2 = new Node(14);
		Node w2 = new Node(15);
		assertEquals("Checking LCA in balanced tree where some parent is LCA", balancedAndLongTree.root.rightChild.rightChild, balancedAndLongTree.findLCA(v2.data, w2.data));
		
		DAG longNarrowTree = new DAG();
		longNarrowTree.populateLongNarrowTree(longNarrowTree);
		Node l1 = new Node(9);
		Node m1 = new Node(8);
		assertEquals("Checking LCA in long, narrow tree where some parent is LCA", longNarrowTree.root.leftChild.rightChild, longNarrowTree.findLCA(l1.data, m1.data));
	}	
	
	@Test
	public void testFindLCARootIsLCA() {
		DAG shortTree = new DAG();
		shortTree.populateShortTree(shortTree);
		Node s = new Node(10);
		Node t = new Node(4);
		assertEquals("Checking LCA in a short tree where root is the LCA", shortTree.root, shortTree.findLCA(s.data, t.data));

		DAG balancedAndLongTree = new DAG();
		balancedAndLongTree.populateBalancedAndLongTree(balancedAndLongTree);
		Node v = new Node(9);
		Node w = new Node(8);
		assertEquals("Checking LCA in a balanced tree where root is the LCA", balancedAndLongTree.root, balancedAndLongTree.findLCA(v.data, w.data));
		
		DAG longNarrowTree = new DAG();
		longNarrowTree.populateLongNarrowTree(longNarrowTree);
		Node l1 = new Node(8);
		Node m1 = new Node(10);
		assertEquals("Checking LCA in a long, narrow tree where root is the LCA", longNarrowTree.root, longNarrowTree.findLCA(l1.data, m1.data));
	}
	
	@Test
	public void testfindLCAVIsAncestor() {
		DAG treeLeaningRight = new DAG();
		treeLeaningRight.populateRightLeaningTree(treeLeaningRight);
		Node v = treeLeaningRight.root;
		Node w = null;
		if(treeLeaningRight.root.leftChild!=null) {
			w = treeLeaningRight.root.leftChild;
		}
		else if(treeLeaningRight.root.rightChild!=null) {
			w = treeLeaningRight.root.rightChild;
		}
		assertEquals("Checking LCA in a right leaning tree where V (node1) is the LCA", v, treeLeaningRight.findLCA(v.data, w.data));
		
		DAG balancedAndLongTree = new DAG();
		balancedAndLongTree.populateBalancedAndLongTree(balancedAndLongTree);
		Node v2 = balancedAndLongTree.root;
		Node w2 = null;
		if(balancedAndLongTree.root.leftChild!=null) {
			w2 = balancedAndLongTree.root.leftChild;
		}
		else if(balancedAndLongTree.root.rightChild!=null) {
			w2 = balancedAndLongTree.root.rightChild;
		}
		assertEquals("Checking LCA in a balanced tree where V (node1) is the LCA", v2, balancedAndLongTree.findLCA(v2.data, w2.data));
		
		DAG longNarrowTree = new DAG();
		longNarrowTree.populateLongNarrowTree(longNarrowTree);
		Node l1 = new Node(4);
		Node m1 = new Node(8);
		assertEquals("Checking LCA in a long, narrow tree where V is the LCA", longNarrowTree.root.leftChild, longNarrowTree.findLCA(l1.data, m1.data));
	}
	
	@Test
	public void testfindLCAWIsAncestor() {
		DAG treeLeaningRight = new DAG();
		treeLeaningRight.populateRightLeaningTree(treeLeaningRight);
		Node w = treeLeaningRight.root;
		Node v = null;
		if(treeLeaningRight.root.leftChild!=null) {
			v = treeLeaningRight.root.leftChild;
		}
		else if(treeLeaningRight.root.rightChild!=null) {
			v = treeLeaningRight.root.rightChild;
		}
		assertEquals("Checking LCA in a right leaning tree where W (node2) is the LCA", w, treeLeaningRight.findLCA(v.data, w.data));
		
		DAG balancedAndLongTree = new DAG();
		balancedAndLongTree.populateBalancedAndLongTree(balancedAndLongTree);
		Node w2 = balancedAndLongTree.root;
		Node v2 = null;
		if(balancedAndLongTree.root.leftChild!=null) {
			v2 = balancedAndLongTree.root.leftChild;
		}
		else if(balancedAndLongTree.root.rightChild!=null) {
			v2 = balancedAndLongTree.root.rightChild;
		}
		assertEquals("Checking LCA in a balanced tree where W (node2) is the LCA", w2, balancedAndLongTree.findLCA(v2.data, w2.data));
		
		DAG longNarrowTree = new DAG();
		longNarrowTree.populateLongNarrowTree(longNarrowTree);
		Node l1 = new Node(8);
		Node m1 = new Node(4);
		assertEquals("Checking LCA in a long, narrow tree where W is the LCA", longNarrowTree.root.leftChild, longNarrowTree.findLCA(l1.data, m1.data));
	}
	
	@Test
	public void testfindLCAVAndWEqual() {
		DAG balancedAndLongTree = new DAG();
		balancedAndLongTree.populateBalancedAndLongTree(balancedAndLongTree);
		Node v = balancedAndLongTree.root.leftChild.leftChild;
		Node w = balancedAndLongTree.root.leftChild.leftChild;
		assertEquals("Checking LCA in a balanced tree where V and W are the same node", v, balancedAndLongTree.findLCA(v.data, w.data));
		
		DAG longNarrowTree = new DAG();
		longNarrowTree.populateLongNarrowTree(longNarrowTree);
		Node l1 = new Node(4);
		Node m1 = new Node(4);
		assertEquals("Checking LCA in a long, narrow tree where V and W are equal", longNarrowTree.root.leftChild, longNarrowTree.findLCA(l1.data, m1.data));
	}
	
	@Test
	public void testfindLCAVNotAValidNode() {
		DAG balancedAndLongTree = new DAG();
		balancedAndLongTree.populateBalancedAndLongTree(balancedAndLongTree);
		Node v = new Node(20);
		Node w = new Node(8);
		assertEquals("Checking LCA in a balanced tree where V (node1) is not a valid node", null, balancedAndLongTree.findLCA(v.data, w.data));
		
		DAG longNarrowTree = new DAG();
		longNarrowTree.populateLongNarrowTree(longNarrowTree);
		Node l1 = new Node(11);
		Node m1 = new Node(8);
		assertEquals("Checking LCA in a long, narrow tree where V is not a valid node", null, longNarrowTree.findLCA(l1.data, m1.data));
	}
	
	@Test
	public void testfindLCAWNotAValidNode() {
		DAG balancedAndLongTree = new DAG();
		balancedAndLongTree.populateBalancedAndLongTree(balancedAndLongTree);
		Node w = new Node(20);
		Node v = new Node(8);
		assertEquals("Checking LCA in a balanced tree where W (node2) is not a valid node", null, balancedAndLongTree.findLCA(v.data, w.data));
		
		DAG longNarrowTree = new DAG();
		longNarrowTree.populateLongNarrowTree(longNarrowTree);
		Node l1 = new Node(4);
		Node m1 = new Node(11);
		assertEquals("Checking LCA in a long, narrow tree where W is not a valid node", null, longNarrowTree.findLCA(l1.data, m1.data));
	}
	
}
