# recursive DFS

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
un_visited_vertex = None

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
	global un_visited_vertex		  # are visited #################
	a_n_visited = True			      
	for vtx in vertices:
		if vtx.color != "black":
			a_n_visited = False
			un_visited_vertex = vtx.item
			break
	return a_n_visited
			
	
def DFS_vst(v):
	global vertices
	global main_time
	
	if vertices[v -1].color == "white":
		visit_vtx(vertices[v -1])
		if vertices[v -1].color == "grey" and (not all_visited(vertices[v -1])):
			while not all_visited(vertices[v -1]):
				vtx = vertices[v -1]
				while vtx.next != None:
					vtx = vtx.next
					if vertices[vtx.item - 1].color == "white":
						DFS_vst(vtx.item)
			
				
	if vertices[v -1].color == "grey" and all_visited(vertices[v -1]):
		vertices[v -1].color = "black"
		vertices[v -1].fin = main_time
		main_time += 1
		print "backtracking " + str(vertices[v -1].item), "(",vertices[v -1].dis,"/",vertices[v -1].fin,")"
				
def DFS(v):
	global vertices
	global main_time
	while not all_nodes_are_visited(vertices):
		DFS_vst(v)
		v = un_visited_vertex
		
		
DFS(input("Enter the vertex you want to start travel: "))



