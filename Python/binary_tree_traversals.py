# Graph traversals ###################################################

# Pre Order traversal ############
def pre_trav(root):
	print root.item
	node_left = root.left
	if node_left != None:
		pre_trav(node_left)
	node_right = root.right
	if node_right != None:
		pre_trav(node_right)
		
# In Oder traversal ############		
def in_trav(root):
	node_left = root.left
	if node_left != None:
		in_trav(node_left)
	print root.item
	node_right = root.right
	if node_right != None:
		in_trav(node_right)

# Post Order traversal #############		
def post_trav(root):
	node_left = root.left
	if node_left != None:
		post_trav(node_left)
	node_right = root.right
	if node_right != None:
		post_trav(node_right)
	print root.item

###################################	
