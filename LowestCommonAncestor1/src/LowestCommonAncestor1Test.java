// @ author: Stephen Davis
import static org.junit.Assert.*;

import org.junit.Test;
import java.util.ArrayList;

public class LowestCommonAncestor1Test {

	@Test
	public void testCheckIsNode() {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(6);
		tree.root.leftChild = new Node(4);
		tree.root.rightChild = new Node(10);
		tree.root.leftChild.leftChild = new Node(3);
		tree.root.leftChild.rightChild = new Node(5);
		tree.root.leftChild.leftChild.leftChild = new Node(1);
		Node n = new Node(5);
		Node n2 = new Node(16);
		assertEquals(true, tree.checkIsNode(tree.root, n));
		assertEquals(false, tree.checkIsNode(tree.root, n2));
	}
	
	@Test
	public void testEmptyTree() {
		BinaryTree bt = new BinaryTree();
		assertEquals(null, bt.findLCA(3, 4));
	}
//	
//	@Test
//	public void testFindLowestCommonAncestorSomeParent() {
//		Node root = new Node(1);
//		Node v = new Node(5);
//		Node w = new Node(8);
//		assertEquals(root, LowestCommonAncestor1.findLowestCommonAncestor(root, v, w));
//	}
//	
//	@Test
//	public void testFindLowestCommonAncestorVIsAncestor() {
//		Node root = new Node(1);
//		Node v = new Node(4);
//		Node w = new Node(8);
//		assertEquals(v, LowestCommonAncestor1.findLowestCommonAncestor(root, v, w));
//	}
//	
//	@Test
//	public void testFindLowestCommonAncestorWIsAncestor() {
//		Node root = new Node(1);
//		Node v = new Node(8);
//		Node w = new Node(4);
//		assertEquals(w, LowestCommonAncestor1.findLowestCommonAncestor(root, v, w));
//	}
//	
//	@Test
//	public void testFindLowestCommonAncestorVAndWEqual() {
//		Node root = new Node(1);
//		Node v = new Node(5);
//		Node w = new Node(5);
//		assertEquals(v, LowestCommonAncestor1.findLowestCommonAncestor(root, v, w));
//	}
//	
//	@Test
//	public void testFindLowestCommonAncestorVNotAValidNode() {
//		Node root = new Node(1);
//		Node v = new Node(5);
//		Node w = new Node(8);
//		assertEquals(null, LowestCommonAncestor1.findLowestCommonAncestor(root, v, w));
//	}
//	
//	@Test
//	public void testFindLowestCommonAncestorWNotAValidNode() {
//		Node root = new Node(1);
//		Node v = new Node(5);
//		Node w = new Node(8);
//		assertEquals(null, LowestCommonAncestor1.findLowestCommonAncestor(root, v, w));
//	}
//	
}
