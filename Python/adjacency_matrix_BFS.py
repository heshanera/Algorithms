# Tutorial 04

# (01)


class Adj_Ma_Node():
	def __init__(self,item):
		self.item = item
		self.color = "white"
		self.dis = None				#discover_time
		self.pre = None				#predecessor

def visit_vtx(v,predcsr,dis_time):
	v.color = "grey"
	v.dis = dis_time
	v.pre = predcsr
	
def vst_adj(matrix,m_lst,v,predcsr,dis_time):
	edg = 0
	for edge in matrix[v.item]:
		if edge == 1 and (vtx_dic[edg].color == "white"):
			print "v" + str(edg+1), edg
			visit_vtx(vtx_dic[edg],predcsr,dis_time)   #dic[edg]
			predcsr = v
			dis_time += 1 
			m_lst.append(vtx_dic[edg])
		edg += 1	
	m_lst[0].color = "black"
	m_lst.pop(0)

def BFS(matrix,v):
	predcsr = None
	dis_time = 1
	print "v"+ str(v.item + 1)
	visit_vtx(v,predcsr,dis_time)
	vtx_lst = [v]
	vst_adj(matrix,vtx_lst,v,predcsr,dis_time)
	while len(vtx_lst) != 0:
		vst_adj(matrix,vtx_lst,vtx_lst[0],predcsr,dis_time)

# 0 1 1 0 0
# 0 0 1 0 0
# 0 0 0 0 1
# 1 0 0 0 1
# 0 0 0 0 0


m1 = 	[
			[0,1,1,0,0],
			[0,0,1,0,0],
			[0,0,0,0,1],
			[1,0,0,0,1],
			[0,0,0,0,0]
		]
v1 = Adj_Ma_Node(0)	
v2 = Adj_Ma_Node(1)
v3 = Adj_Ma_Node(2)
v4 = Adj_Ma_Node(3)
v5 = Adj_Ma_Node(4)
	
vtx_dic = {
			0 : v1,
			1 : v2,
			2 : v3,
			3 : v4,
			4 : v5,
			}				
			
			
BFS(m1,v4)
	
				

