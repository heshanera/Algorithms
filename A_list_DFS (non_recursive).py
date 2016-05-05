#non recursive DFS

# nodes ###########################################################
class Adj_lst_Node():
	def __init__(self,item):
		self.item = item
		self.color = "white"
		self.next = None
		self.pre = None			# predecessor #####################
		self.dis = None			# dicovered_time ##################
		self.fin = None			# finished_time ###################

class smpl_node():
	def __init__(self,item):
		self.item = item
		self.next = None


# Creating the graph with list #####################################
V,E = map(int, raw_input().split())
vertices = []
edges = [] 

vtx = 1
while vtx < V+1:
	vertices.append(Adj_lst_Node(vtx))
	vtx += 1

edge = 0
while edge < E:
	_edge = raw_input()
	edges.append(_edge)
	edge += 1

	
for edge in edges:
	vtx1, vtx2 = map(int, edge.split(" "))
	tmp_vtx = vertices[vtx1-1]
	while tmp_vtx.next != None:
		tmp_vtx = tmp_vtx.next
	tmp_vtx.next = smpl_node(vtx2)


# printing the adjecency linked list #############################
for vtx in vertices:
	tmp_lst = [vtx.item]
	while vtx.next != None:
		tmp_lst.append(vtx.next.item)
		vtx = vtx.next
	print tmp_lst[0], " ---> ", tmp_lst[1:]
	
###################################################################

# Visiting the list in DFS ########################################

visited = None
main_time = 1
un_visited_vtx = None

def all_visited(v):  # checking the node that it visited all it's #
	a_vis = True     # adjecent vertices ##########################
	node = v
	while node.next != None:
		node = node.next
		if vertices[node.item - 1].color == "white":
			a_vis = False
			break
	return a_vis

def visit_vtx(v):    # visiting the node 'v' ######################
	global visited
	global main_time
	v.color = "grey"
	v.pre = visited
	v.dis = main_time
	visited = v
	main_time += 1
	print visited.item


def all_nodes_are_visited(vertices):  # checking all the list nodes # 
	global un_visited_vtx             # are visited #################
	a_n_visited = True			   
	for vtx in vertices:
		if vtx.color == "white":
			a_n_visited = False
			un_visited_vtx = vtx.item
			break
	return a_n_visited
	
	
def visit_DFS(v):	
	global vertices		       # DFS visit ###################
	global visited
	global main_time
	v = vertices[v-1]
	while (v.color == "white"):
		visit_vtx(v)
		_node = v
		found = False
		while v.next != None:
			v = v.next
			if vertices[v.item - 1].color == "white":
				found = True
				break
		
		if found == True:
			v = vertices[v.item - 1]
		else:
			v = _node	
	
	while (v != None) and (all_visited(v)) and (v.color == "grey"):
		v.color  = "black"
		v.fin = main_time
		main_time += 1
		print "backtracking " + str(v.item), "(", v.dis, "/", v.fin, ")"
		v = v.pre
	
	if v != None:		
		if (not all_visited(v)):
			visited = v
			while v.next != None:
				v = v.next
				if vertices[v.item - 1].color == "white":
					v = vertices[v.item - 1]
					break

def DFS(v):
	global un_visited_vtx
	global vertices	
	global visited
	global main_time
	visit_DFS(v)
	while not all_nodes_are_visited(vertices):
		v = un_visited_vtx
		visit_DFS(v)
#####################################################################

DFS(input("Enter the vertex you want to start travel: "))
