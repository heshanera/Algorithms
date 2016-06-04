# inserting data into a binary tree

class Node():
	def __init__(self,item):
		self.item = item
		self.parent = None
		self.left = None
		self.right = None

# inserting a node to an existing tree #######################
def insert(item,root):
	node = root
	while True:
		node_item = node.item
		if item < node_item:
			if node.left == None:
				tmp = Node(item)
				tmp.parent = node
				node.left = tmp
				break
			else:
				node = node.left
		elif item > node_item:
			if node.right == None:
				tmp = Node(item)
				tmp.parent = node
				node.right = tmp
				break
			else:
				node = node.right
		else:
			print "node is already in the tree"
			break

# creating a tree using list of nodes #########################
Root = None
def create_a_tree(lst):
	global Root
	root = Node(lst[0])
	Root = root
	for token in lst[1:]:
		insert(token,root)

# binary searching	###########################################
def bin_search(item,root):
	node = root
	while True:
		node_item = node.item
		if item < node_item:
			if node.left == None:
				print "Not found"
				break
			else:
				node = node.left
		elif item > node_item:
			if node.right == None:
				print "Not found"
				break
			else:
				node = node.right
		else:
			print "found"
			break
	
#################################################################	
			
vals = [7,8,5,7,4,6,3,2,1,9,4]
create_a_tree(vals)
bin_search(10,Root)
			
