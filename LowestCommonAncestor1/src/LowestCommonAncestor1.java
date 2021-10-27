public class LowestCommonAncestor1 {

	/* Driver program to test LCA functions */
	public static void main(String args[])
	{
		DAG graph = new DAG(8);
		graph.populateLongNarrowGraph(graph);

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
