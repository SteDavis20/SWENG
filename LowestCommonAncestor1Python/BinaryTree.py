# @author: adapted from Nikhil Kumar Singh(nickzuck_007) from GeeksForGeeks (original author but code
# had lots of errors and bugs, e.g., calling functions recursively without calling using "self"
# keyword which led to "function not defined" errors where could would not compile).
from Node import Node

class BinaryTree:

    def __init__(self):
        self.root = None

    # This function return pointer to LCA of two given values
    # n1 and n2
    # v1 is set as true by this function if n1 is found
    # v2 is set as true by this function if n2 is found
    def findLCAUtil(self, root, n1, n2, v):

        # Base Case
        if root is None:
            return None

        # IF either n1 or n2 matches ith root's data, report
        # the presence by setting v1 or v2 as true and return
        # root (Note that if a data is ancestor of other, then
        # the ancestor data becomes LCA)
        if root.data == n1:
            v[0] = True
            return root

        if root.data == n2:
            v[1] = True
            return root

        # Look for datas in leftChild and rightChild subtree
        leftChild_lca = self.findLCAUtil(root.leftChild, n1, n2, v)
        rightChild_lca = self.findLCAUtil(root.rightChild, n1, n2, v)

        # If both of the above calls return Non-NULL, then one data
        # is present in one subtree and other is present in other,
        # So this node is the LCA
        if leftChild_lca and rightChild_lca:
            return root

        # Otherwise check if leftChild subtree or rightChild subtree is LCA
        return leftChild_lca if leftChild_lca is not None else rightChild_lca

    def find(self, root, k):

        # Base Case
        if root is None:
            return False

        # If data is present at root, or if leftChild subtree or rightChild
        # subtree , return true
        if (root.data == k or self.find(root.leftChild, k) or
                self.find(root.rightChild, k)):
            return True

        # Else return false
        return False

    # This function returns LCA of n1 and n2 onlue if both
    # n1 and n2 are present in tree, otherwise returns None
    def findLCA(self, root, n1, n2):

        # Initialize n1 and n2 as not visited
        v = [False, False]

        # Find lac of n1 and n2 using the technique discussed above
        lca = self.findLCAUtil(root, n1, n2, v)

        # Returns LCA only if both n1 and n2 are present in tree
        if (v[0] and v[1] or v[0] and self.find(lca, n2) or v[1] and
                self.find(lca, n1)):
            return lca

        # Else return None
        return None

    # @author: Stephen Davis
    # Below 4 methods are used to populate various different kinds of trees for unit testing
    def populateLeftLeaningTree(self):
        self.root = Node(6)
        self.root.leftChild = Node(4)
        self.root.leftChild.leftChild = Node(10)
        self.root.leftChild.leftChild.leftChild = Node(3)
        self.root.leftChild.leftChild.leftChild.leftChild = Node(5)
    
    #@author: Stephen Davis
    def populateRightLeaningTree(self):
        self.root = Node(6)
        self.root.rightChild = Node(4)
        self.root.rightChild.rightChild = Node(10)
        self.root.rightChild.rightChild.rightChild = Node(3)
        self.root.rightChild.rightChild.rightChild.rightChild = Node(5)
        
    # @author: Stephen Davis
    def populateBalancedAndLongTree(self):
        self.root = Node(6)
        self.root.leftChild = Node(4)
        self.root.rightChild = Node(10)
        self.root.leftChild.leftChild = Node(3)
        self.root.leftChild.rightChild = Node(5)
        self.root.rightChild.leftChild = Node(12)
        self.root.rightChild.rightChild = Node(11)
        self.root.leftChild.rightChild.rightChild = Node(2)
        self.root.leftChild.rightChild.leftChild = Node(7)
        self.root.leftChild.leftChild.leftChild = Node(1)
        self.root.leftChild.leftChild.rightChild = Node(9)
        self.root.rightChild.leftChild.leftChild = Node(8)
        self.root.rightChild.leftChild.rightChild = Node(13)
        self.root.rightChild.rightChild.leftChild = Node(14)
        self.root.rightChild.rightChild.rightChild = Node(15)
    
    # @author: Stephen Davis
    def populateShortTree(self):
        self.root = Node(6)
        self.root.leftChild = Node(4)
        self.root.rightChild = Node(10)
        
    # @author: Stephen Davis
    def populateLongNarrowTree(self):
        self.root = Node(6)
        self.root.leftChild = Node(4)
        self.root.rightChild = Node(10)
        self.root.leftChild.rightChild = Node(5)
        self.root.leftChild.rightChild.leftChild = Node(7)
        self.root.leftChild.rightChild.leftChild.rightChild = Node(2)
        self.root.leftChild.rightChild.leftChild.rightChild.leftChild = Node(8)
        self.root.leftChild.rightChild.rightChild = Node(1)
        self.root.leftChild.rightChild.rightChild.rightChild = Node(3)
        self.root.leftChild.rightChild.rightChild.rightChild.leftChild = Node(9)

    