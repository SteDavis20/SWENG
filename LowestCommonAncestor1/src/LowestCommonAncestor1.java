public class LowestCommonAncestor1 {

	/* Driver program to test LCA functions */
	public static void main(String args[])
	{
		DAG graph = new DAG();
		graph.root = new Node(1);
		graph.root.leftChild = new Node(2);
		graph.root.rightChild = new Node(3);
		graph.root.leftChild.leftChild = new Node(4);
		graph.root.leftChild.rightChild = new Node(5);
		graph.root.rightChild.leftChild = new Node(6);
		graph.root.rightChild.rightChild = new Node(7);

		Node lca = graph.findLCA(4, 5);
		if (lca != null)
			System.out.println("LCA(4, 5) = " + lca.data);
		else
			System.out.println("Keys are not present");

		lca = graph.findLCA(4, 10);
		if (lca != null)
			System.out.println("LCA(4, 10) = " + lca.data);
		else
			System.out.println("Keys are not present");
	}
}
