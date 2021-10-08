// @ author: Stephen Davis
import static org.junit.Assert.*;

import org.junit.Test;
import java.util.ArrayList;

public class LowestCommonAncestor1Test {

	public static void main(String[] args) {
		
		// populating various different kinds of trees
		BinaryTree emptyTree = new BinaryTree();
		
		BinaryTree treeLeaningLeft = new BinaryTree();
		treeLeaningLeft.root = new Node(6);
		treeLeaningLeft.root.leftChild = new Node(4);
		treeLeaningLeft.root.leftChild.leftChild = new Node(10);
		treeLeaningLeft.root.leftChild.leftChild.leftChild = new Node(3);
		treeLeaningLeft.root.leftChild.leftChild.leftChild.leftChild = new Node(5);
		
		BinaryTree treeLeaningRight = new BinaryTree();
		treeLeaningRight.root = new Node(6);
		treeLeaningRight.root.rightChild = new Node(4);
		treeLeaningRight.root.rightChild.rightChild = new Node(10);
		treeLeaningRight.root.rightChild.rightChild.rightChild = new Node(3);
		treeLeaningRight.root.rightChild.rightChild.rightChild.rightChild = new Node(5);
		
		BinaryTree balancedAndLongTree = new BinaryTree();
		balancedAndLongTree.root = new Node(6);
		balancedAndLongTree.root.leftChild = new Node(4);
		balancedAndLongTree.root.rightChild = new Node(10);
		balancedAndLongTree.root.leftChild.leftChild = new Node(3);
		balancedAndLongTree.root.leftChild.rightChild = new Node(5);
		balancedAndLongTree.root.rightChild.leftChild = new Node(12);
		balancedAndLongTree.root.rightChild.rightChild = new Node(11);
		balancedAndLongTree.root.leftChild.rightChild.rightChild = new Node(2);
		balancedAndLongTree.root.leftChild.rightChild.leftChild = new Node(7);
		balancedAndLongTree.root.leftChild.leftChild.leftChild = new Node(1);
		balancedAndLongTree.root.leftChild.leftChild.rightChild = new Node(9);
		balancedAndLongTree.root.rightChild.leftChild.leftChild = new Node(8);
		balancedAndLongTree.root.rightChild.leftChild.rightChild = new Node(13);
		balancedAndLongTree.root.rightChild.rightChild.leftChild = new Node(14);
		balancedAndLongTree.root.rightChild.rightChild.rightChild = new Node(15);
		
		
		BinaryTree shortTree = new BinaryTree();
		shortTree.root = new Node(6);
		shortTree.root.leftChild = new Node(4);
		shortTree.root.rightChild = new Node(10);
		
	}
	
	@Test
	public void testCheckIsNode(BinaryTree tree) {
		Node n = new Node(5);
		Node n2 = new Node(16);
		assertEquals(true, tree.checkIsNode(tree.root, n));
		assertEquals(false, tree.checkIsNode(tree.root, n2));
	}
	
	@Test
	public void testEmptyTree(BinaryTree tree) {
		assertEquals(null, tree.findLCA(3, 4));
	}
	
	// TO-DO
//	@Test
//	public void testfindLCASomeParent(BinaryTree tree) {
//		Node root = new Node(1);
//		Node v = new Node(5);
//		Node w = new Node(8);
//		assertEquals(root, BinaryTree.findLCA(v, w));
//	}
////	
	
	@Test
	public void testfindLCAVIsAncestor(BinaryTree tree) {
		Node v = tree.root;
		Node w = null;
		if(tree.root.leftChild!=null) {
			w = tree.root.leftChild;
		}
		else if(tree.root.rightChild!=null) {
			w = tree.root.rightChild;
		}
		assertEquals(v, tree.findLCA(v.data, w.data));
	}
	
	@Test
	public void testfindLCAWIsAncestor(BinaryTree tree) {
		Node w = tree.root;
		Node v = null;
		if(tree.root.leftChild!=null) {
			v = tree.root.leftChild;
		}
		else if(tree.root.rightChild!=null) {
			v = tree.root.rightChild;
		}
		assertEquals(v, tree.findLCA(v.data, w.data));
	}
	
	@Test
	public void testfindLCAVAndWEqual(BinaryTree tree) {
		Node v = tree.root.leftChild.leftChild;
		Node w = tree.root.leftChild.leftChild;
		assertEquals(v, tree.findLCA(v.data, w.data));
	}
	
//	@Test
//	public void testfindLCAVNotAValidNode(BinaryTree tree) {
//		Node root = new Node(1);
//		Node v = new Node(5);
//		Node w = new Node(8);
//		assertEquals(null, BinaryTree.findLCA(root, v, w));
//	}
//	
}
