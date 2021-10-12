public class LowestCommonAncestor1 {

	/* Driver program to test LCA functions */
	public static void main(String args[])
	{
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(1);
		tree.root.leftChild = new Node(2);
		tree.root.rightChild = new Node(3);
		tree.root.leftChild.leftChild = new Node(4);
		tree.root.leftChild.rightChild = new Node(5);
		tree.root.rightChild.leftChild = new Node(6);
		tree.root.rightChild.rightChild = new Node(7);

		Node lca = tree.findLCA(4, 5);
		if (lca != null)
			System.out.println("LCA(4, 5) = " + lca.data);
		else
			System.out.println("Keys are not present");

		lca = tree.findLCA(4, 10);
		if (lca != null)
			System.out.println("LCA(4, 10) = " + lca.data);
		else
			System.out.println("Keys are not present");
	}
}
