# Adjecency lists BFS

# nodes ###########################################################
class Adj_lst_Node():
	def __init__(self,item):
		self.item = item
		self.color = "white"
		self.next = None
		self.pre = None			# predecessor #####################
		self.dis = None			# dicovered_time ##################

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
	
 # Visiting the graph in BFS ########################################

visited = None
main_time = 1
t_main = None
 
def visit_vtx(v):      # visiting the node 'v' ######################
	global main_time
	v.color = "black"
	if v.pre != None:
		v.dis = vertices[v.pre-1].dis + 1	
	else:
		v.dis = 0
	print v.item, v.dis
	

def all_nodes_are_visited(vertices):  # checking all the list nodes # 
	global un_visited_vtx             # are visited #################
	a_n_visited = True			   
	for vtx in vertices:
		if vtx.color == "white":
			a_n_visited = False
			un_visited_vtx = vtx.item
			break
	return a_n_visited


def vst_BFS(v):
	global vertices
	global _dis
	global main_time
	to_vst_q = []
	vertices[v-1].color = "grey"
	to_vst_q.append(v)
	while len(to_vst_q) > 0:
		to_vst_q.pop(0)
		if vertices[v-1].color == "grey":
			visit_vtx(vertices[v-1])
				
		vtx = vertices[v-1]
		_pre = v
		_dis = vtx.dis
		while vtx.next != None:
			vtx = vtx.next
			if vertices[vtx.item - 1].color == "white":
				vertices[vtx.item - 1].color = "grey"
				vertices[vtx.item - 1].pre = _pre
				to_vst_q.append(vtx.item)
		
		if len(to_vst_q) > 	0:
			v = to_vst_q[0]

		
	 
def BFS(v):
	global vertices
	global un_visited_vtx
	vst_BFS(v)
	while not all_nodes_are_visited(vertices):
		v = un_visited_vtx
		vst_BFS(v)

BFS(1)	

