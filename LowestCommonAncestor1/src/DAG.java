// @author: Dhruv (from GeeksforGeeks)
// @author: Stephen Davis

// adapted from Dhruv's work (from GeeksForGeeks).

// Java implementation to find lowest common ancestor of
// n1 and n2 using one traversal of binary tree
// It also handles cases even when n1 and n2 are not there in Tree

public class DAG
{
	// Root of the Binary Tree
	Node root;
	static boolean v1 = false, v2 = false;					// used to check if nodes are in tree

	// This function returns pointer to LCA of two given
	// values n1 and n2.
	// v1 is set as true by this function if n1 is found
	// v2 is set as true by this function if n2 is found
	Node findLCAUtil(Node node, int n1, int n2)
	{
		// Base case
		if (node == null)
			return null;

		//Store result in temp, in case of key match so that we can search for other key also.
		Node temp=null;

		// If either n1 or n2 matches with root's key, report the presence
		// by setting v1 or v2 as true and return root (Note that if a key
		// is ancestor of other, then the ancestor key becomes LCA)
		if (node.data == n1)
		{
			v1 = true;
			temp = node;
		}
		if (node.data == n2)
		{
			v2 = true;
			temp = node;
		}

		// Look for keys in left and right subtrees
		Node left_lca = findLCAUtil(node.leftChild, n1, n2);
		Node right_lca = findLCAUtil(node.rightChild, n1, n2);

		if (temp != null)
			return temp;

		// If both of the above calls return Non-NULL, then one key
		// is present in one subtree and other is present in other,
		// So this node is the LCA
		if (left_lca != null && right_lca != null)
			return node;

		// Otherwise check if left subtree or right subtree is LCA
		return (left_lca != null) ? left_lca : right_lca;
	}

	// Finds lca of n1 and n2 under the subtree rooted with 'node'
	Node findLCA(int n1, int n2)
	{
		// Initialize n1 and n2 as not visited
		v1 = false;
		v2 = false;

		// Find lca of n1 and n2 using the technique discussed above
		Node lca = findLCAUtil(root, n1, n2);

		// Return LCA only if both n1 and n2 are present in tree
		if (v1 && v2)
			return lca;

		// Else return NULL
		return null;
	}

	// @author: Stephen Davis
	public boolean checkIsNode(Node root, Node n) {
		if(root==null) {
			return false;
		}
		if(root.data==n.data) {
			return true;
		}
		if(checkIsNode(root.leftChild, n)) {
			return true;
		}
		else if(checkIsNode(root.rightChild, n)) {
			return true;
		}
		return false;
	}
	
	// @author: Stephen Davis
	// Below 4 methods are used to populate various different kinds of trees for unit testing
	public void populateLeftLeaningTree(DAG tree) {
		tree.root = new Node(6);
		tree.root.leftChild = new Node(4);
		tree.root.leftChild.leftChild = new Node(10);
		tree.root.leftChild.leftChild.leftChild = new Node(3);
		tree.root.leftChild.leftChild.leftChild.leftChild = new Node(5);
	}
	
	// @author: Stephen Davis
	public void populateRightLeaningTree(DAG tree) {
		tree.root = new Node(6);
		tree.root.rightChild = new Node(4);
		tree.root.rightChild.rightChild = new Node(10);
		tree.root.rightChild.rightChild.rightChild = new Node(3);
		tree.root.rightChild.rightChild.rightChild.rightChild = new Node(5);
	}
	
	// @author: Stephen Davis
	public void populateBalancedAndLongTree(DAG tree) {
		tree.root = new Node(6);
		tree.root.leftChild = new Node(4);
		tree.root.rightChild = new Node(10);
		tree.root.leftChild.leftChild = new Node(3);
		tree.root.leftChild.rightChild = new Node(5);
		tree.root.rightChild.leftChild = new Node(12);
		tree.root.rightChild.rightChild = new Node(11);
		tree.root.leftChild.rightChild.rightChild = new Node(2);
		tree.root.leftChild.rightChild.leftChild = new Node(7);
		tree.root.leftChild.leftChild.leftChild = new Node(1);
		tree.root.leftChild.leftChild.rightChild = new Node(9);
		tree.root.rightChild.leftChild.leftChild = new Node(8);
		tree.root.rightChild.leftChild.rightChild = new Node(13);
		tree.root.rightChild.rightChild.leftChild = new Node(14);
		tree.root.rightChild.rightChild.rightChild = new Node(15);
	}
	
	// @author: Stephen Davis
	public void populateShortTree(DAG tree) {
		tree.root = new Node(6);
		tree.root.leftChild = new Node(4);
		tree.root.rightChild = new Node(10);
	}
	
	// @author: Stephen Davis
		public void populateLongNarrowTree(DAG tree) {
			tree.root = new Node(6);
			tree.root.leftChild = new Node(4);
			tree.root.rightChild = new Node(10);
			tree.root.leftChild.rightChild = new Node(5);
			tree.root.leftChild.rightChild.leftChild = new Node(7);
			tree.root.leftChild.rightChild.leftChild.rightChild = new Node(2);
			tree.root.leftChild.rightChild.leftChild.rightChild.leftChild = new Node(8);
			tree.root.leftChild.rightChild.rightChild = new Node(1);
			tree.root.leftChild.rightChild.rightChild.rightChild = new Node(3);
			tree.root.leftChild.rightChild.rightChild.rightChild.leftChild = new Node(9);
		}

}