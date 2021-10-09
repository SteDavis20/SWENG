import unittest
from BinaryTree import BinaryTree
from Node import Node   # The test framework

class BinaryTreeTest(unittest.TestCase):
	
	# list test functions below:

	def testEmptyTree(self):
		tree = BinaryTree()
		self.assertEqual(None, tree.findLCA(tree.root, 3, 4))
	
	def testfindLCASomeParent(self):
		balancedAndLongTree = BinaryTree()
		balancedAndLongTree.populateBalancedAndLongTree()
		
		v = Node(1)
		w = Node(7)
		self.assertEqual(balancedAndLongTree.root.leftChild, balancedAndLongTree.findLCA(balancedAndLongTree.root, v.data, w.data))
		
		v2 = Node(14)
		w2 = Node(15)
		self.assertEqual(balancedAndLongTree.root.rightChild.rightChild, balancedAndLongTree.findLCA(balancedAndLongTree.root, v2.data, w2.data))
		
		longNarrowTree = BinaryTree()
		longNarrowTree.populateLongNarrowTree()
		l1 = Node(9)
		m1 = Node(8)
		self.assertEqual(longNarrowTree.root.leftChild.rightChild, longNarrowTree.findLCA(longNarrowTree.root, l1.data, m1.data))

	def testFindLCARootIsLCA(self):
		shortTree = BinaryTree()
		shortTree.populateShortTree()
		s = Node(10)
		t = Node(4)
		self.assertEqual(shortTree.root, shortTree.findLCA(shortTree.root, s.data, t.data))

		balancedAndLongTree = BinaryTree()
		balancedAndLongTree.populateBalancedAndLongTree()
		v = Node(9)
		w = Node(8)
		self.assertEqual(balancedAndLongTree.root, balancedAndLongTree.findLCA(balancedAndLongTree.root, v.data, w.data))
		
		longNarrowTree = BinaryTree()
		longNarrowTree.populateLongNarrowTree()
		l1 = Node(8)
		m1 = Node(10)
		self.assertEqual(longNarrowTree.root, longNarrowTree.findLCA(longNarrowTree.root, l1.data, m1.data))

	def testfindLCAVIsAncestor(self):        
		treeLeaningRight = BinaryTree()
		treeLeaningRight.populateRightLeaningTree()
		v = treeLeaningRight.root
		w = None
		if(treeLeaningRight.root.leftChild!=None):
			w = treeLeaningRight.root.leftChild
		
		elif(treeLeaningRight.root.rightChild!=None):
			w = treeLeaningRight.root.rightChild
		
		self.assertEqual(v, treeLeaningRight.findLCA(treeLeaningRight.root, v.data, w.data))
		
		balancedAndLongTree = BinaryTree()
		balancedAndLongTree.populateBalancedAndLongTree()
		v2 = balancedAndLongTree.root
		w2 = None
		if(balancedAndLongTree.root.leftChild!=None):
			w2 = balancedAndLongTree.root.leftChild

		elif(balancedAndLongTree.root.rightChild!=None):
			w2 = balancedAndLongTree.root.rightChild

		self.assertEqual(v2, balancedAndLongTree.findLCA(balancedAndLongTree.root, v2.data, w2.data))		

	def testfindLCAWIsAncestor(self):
		treeLeaningRight = BinaryTree()
		treeLeaningRight.populateRightLeaningTree()
		w = treeLeaningRight.root
		v = None
		if(treeLeaningRight.root.leftChild!=None):
			v = treeLeaningRight.root.leftChild
		
		elif(treeLeaningRight.root.rightChild!=None):
			v = treeLeaningRight.root.rightChild
		
		self.assertEqual(w, treeLeaningRight.findLCA(treeLeaningRight.root, v.data, w.data))
		
		balancedAndLongTree = BinaryTree()
		balancedAndLongTree.populateBalancedAndLongTree()
		w2 = balancedAndLongTree.root
		v2 = None
		if(balancedAndLongTree.root.leftChild!=None):
			v2 = balancedAndLongTree.root.leftChild

		elif(balancedAndLongTree.root.rightChild!=None):
			v2 = balancedAndLongTree.root.rightChild
		
		self.assertEqual(w2, balancedAndLongTree.findLCA(balancedAndLongTree.root, v2.data, w2.data))

	def testfindLCAVAndWEqual(self):
		balancedAndLongTree = BinaryTree()
		balancedAndLongTree.populateBalancedAndLongTree()
		v = balancedAndLongTree.root.leftChild.leftChild
		w = balancedAndLongTree.root.leftChild.leftChild
		self.assertEqual(v, balancedAndLongTree.findLCA(balancedAndLongTree.root, v.data, w.data))

	def testfindLCAVNotAValidNode(self):
		balancedAndLongTree = BinaryTree()
		balancedAndLongTree.populateBalancedAndLongTree()
		v = Node(20)
		w = Node(8)
		self.assertEqual(None, balancedAndLongTree.findLCA(balancedAndLongTree.root, v.data, w.data))


	def testfindLCAWNotAValidNode(self):
		balancedAndLongTree = BinaryTree()
		balancedAndLongTree.populateBalancedAndLongTree()
		w = Node(20)
		v = Node(8)
		self.assertEqual(None, balancedAndLongTree.findLCA(balancedAndLongTree.root, v.data, w.data))

# call tests
if __name__ == '__main__':
	unittest.main