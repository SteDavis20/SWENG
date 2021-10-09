import unittest

from Node import Node   # The test framework

class BinaryTreeTest(unittest.TestCase):
	
	# list test functions below:

	def testEmptyTree(self):
		n = Node()
		self.assertEqual(None, n.findLCA(3, 4))
	
	def testfindLCASomeParent(self):
		# TO-DO: Populate trees and use these to test function
		self.assertEqual(4, 4)


	def testFindLCARootIsLCA(self):
		# TO-DO: Populate trees and use these to test function
		self.assertEqual(4, 4)


	def testfindLCAVIsAncestor(self):        
		# TO-DO: Populate trees and use these to test function
		self.assertEqual(4, 4)


	def testfindLCAWIsAncestor(self):
		# TO-DO: Populate trees and use these to test function
		self.assertEqual(4, 4)


	def testfindLCAVAndWEqual(self):
		# TO-DO: Populate trees and use these to test function
		self.assertEqual(4, 4)


	def testfindLCAVNotAValidNode(self):
		# TO-DO: Populate trees and use these to test function
		self.assertEqual(4, 4)


	def testfindLCAWNotAValidNode(self):
		# TO-DO: Populate trees and use these to test function
		self.assertEqual(4, 4)


# call tests
if __name__ == '__main__':
	unittest.main