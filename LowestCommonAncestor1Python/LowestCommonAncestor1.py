from BinaryTree import BinaryTree
from Node import Node

class LowestCommonAncestor1:

        # Driver program to test above function
    def main():
        tree = BinaryTree()
        tree.root = Node(1)
        tree.root.leftChild = Node(2)
        tree.root.rightChild = Node(3)
        tree.root.leftChild.leftChild = Node(4)
        tree.root.leftChild.rightChild = Node(5)
        tree.root.rightChild.leftChild = Node(6)
        tree.root.rightChild.rightChild = Node(7)
        lca = tree.findLCA(tree.root, 4, 5)
        
        if lca is not None:
            print("LCA(4, 5) = ", lca.data)
        else:
            print("Node(s) are not present")

        lca = tree.findLCA(tree.root, 4, 10)
        if lca is not None:
            print("LCA(4,10) = ", lca.data)
        else:
            print("Node(s) are not present")

    if __name__ == '__main__': main()