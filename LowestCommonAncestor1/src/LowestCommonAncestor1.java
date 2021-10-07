/*  By Wikipedia definition, a node is a descendant of itself. 
    Hence, LCA of node1 and node2 can be node1 or node2 itself.
*/

/*  v, w are the 2 nodes
    Cases:
            Error
    v is not a node in the graph
    w is not a node in the graph
    v & w are not nodes in the graph
    
            Has Solution
    If both v and w are nodes in the graph then there must be a solution:
    v and w are the same node (return v or w)
    v is an ancestor of w (therefore, v is LCA)
    w is an ancestor of v (therefore, w is LCA)

*/

import java.util.ArrayList;

public class LowestCommonAncestor1 {

    private static ArrayList<Integer> vPath = new ArrayList<Integer>();
    private static ArrayList<Integer> wPath = new ArrayList<Integer>();

    public static void main(String[] args) {

    }

    public static boolean checkIsNode(Node n) {



        return false;
    }
    
    public static ArrayList<Integer> populatePath(Node n) {
        if(checkIsNode(n)) {
        	ArrayList<Integer> path = new ArrayList<Integer>();

        
        	return path;
        }
        return null;
    }

    public static Node findLowestCommonAncestor(Node root, Node v, Node w) {
        if(checkIsNode(v)==false) {
        	return null;
        }
        else if(checkIsNode(w)==false) {
        	return null;
        }
        // if we get here then v and w are both nodes in the graph
    	vPath = populatePath(v);
        wPath = populatePath(w);
        
        // check if v and w are the same node

        
        return null;
    }

}