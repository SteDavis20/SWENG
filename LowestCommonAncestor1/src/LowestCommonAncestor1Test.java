import static org.junit.Assert.*;

import org.junit.Test;
import java.util.ArrayList;

public class LowestCommonAncestor1Test {

	@Test
	public void testCheckIsNodeTrue() {
		Node n = new Node(5);
		assertEquals(true, LowestCommonAncestor1.checkIsNode(n));
	}
	
	@Test
	public void testCheckIsNodeFalse() {
		Node n = null;
		assertEquals(false, LowestCommonAncestor1.checkIsNode(n));
	}


	@Test
	public void testPopulatePathSuccess() {
		ArrayList<Integer> correctPath = new ArrayList<Integer>();
		correctPath.add(1);
		correctPath.add(4);
		correctPath.add(6);
		correctPath.add(8);
		correctPath.add(12);
		Node n = new Node(12);
		assertEquals(correctPath, LowestCommonAncestor1.populatePath(n));
	}
	
	@Test
	public void testPopulatePathFakeNode() {
		Node n = new Node(-5);
		assertEquals(null, LowestCommonAncestor1.populatePath(n));
	}
	
	@Test
	public void testFindLowestCommonAncestorSomeParent() {
		Node root = new Node(1);
		Node v = new Node(5);
		Node w = new Node(8);
		assertEquals(root, LowestCommonAncestor1.findLowestCommonAncestor(root, v, w));
	}
	
	@Test
	public void testFindLowestCommonAncestorVIsAncestor() {
		Node root = new Node(1);
		Node v = new Node(4);
		Node w = new Node(8);
		assertEquals(v, LowestCommonAncestor1.findLowestCommonAncestor(root, v, w));
	}
	
	@Test
	public void testFindLowestCommonAncestorWIsAncestor() {
		Node root = new Node(1);
		Node v = new Node(8);
		Node w = new Node(4);
		assertEquals(w, LowestCommonAncestor1.findLowestCommonAncestor(root, v, w));
	}
	
	@Test
	public void testFindLowestCommonAncestorVAndWEqual() {
		Node root = new Node(1);
		Node v = new Node(5);
		Node w = new Node(5);
		assertEquals(v, LowestCommonAncestor1.findLowestCommonAncestor(root, v, w));
	}
	
	@Test
	public void testFindLowestCommonAncestorVNotAValidNode() {
		Node root = new Node(1);
		Node v = new Node(5);
		Node w = new Node(8);
		assertEquals(null, LowestCommonAncestor1.findLowestCommonAncestor(root, v, w));
	}
	
	@Test
	public void testFindLowestCommonAncestorWNotAValidNode() {
		Node root = new Node(1);
		Node v = new Node(5);
		Node w = new Node(8);
		assertEquals(null, LowestCommonAncestor1.findLowestCommonAncestor(root, v, w));
	}
	
}
