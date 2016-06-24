import java.util.*;
import java.io.*;

public class Kruskal
{
	int noOfVertices;
	int noOfEdges;
	graphNode adjacencyLinkedList[];
	int verticesHeadList[], headerChanges[];
	boolean initial = true;
	
	class graphNode implements Comparable<graphNode>
	{
		graphNode next;
		int nodeValue;
		int weight;
		int index;
		
		public graphNode(int val, int w)
		{
			this.nodeValue = val;
			this.weight = w;
			this.next = null;
		}
		
		@Override
		public int compareTo(graphNode n)
		{
			if (weight > n.weight) return -1;
			else if (weight < n.weight) return +1;
			return 0;
		}
	}
	
	// Creating the Adjacency linked list for the graph
	public graphNode[] createAdjacencyList(String fileName)
	{
		graphNode[] adjacencyList = new graphNode[1];
		String[] tmpString = new String[3];
		try
		{
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String s;
			int i = 0;
			try
			{
				while ((s = br.readLine()) != null)
				{
					tmpString = s.split("\\s+");
					if (i == 0)
					{
						noOfVertices = Integer.parseInt(tmpString[0]);
						noOfEdges = Integer.parseInt(tmpString[1]);
						adjacencyList = new graphNode[noOfVertices];
						i++;
					}
					else
					{
						int index = Integer.parseInt(tmpString[0]) - 1;
						graphNode currentNode = adjacencyList[index];
						int value = (Integer.parseInt(tmpString[1]) - 1);
						int weight = Integer.parseInt(tmpString[2]);
						graphNode newNode = new graphNode(value, weight);
						newNode.index = index;
						
						if (currentNode == null)
						{
							adjacencyList[index] = newNode;
						}
						else
						{
							while (currentNode.next != null)
							{
								currentNode = currentNode.next;
							}
							currentNode.next = newNode;
						}
						
					}
				}
			}
			catch(Exception e)
			{
				System.out.println("Error in the file!");
			}	
			br.close();
			fr.close();
		}
		catch(IOException ex){
		}
		adjacencyLinkedList = adjacencyList;
		return adjacencyList;
	}
	
	
	public graphNode[] sortAdjacentList()
	{
		graphNode[] nodeList = new graphNode[noOfEdges];
		
		int j = 0;
		for (graphNode gNode : adjacencyLinkedList)
		{
			while (gNode != null)
			{
				nodeList[j] = gNode;
				j++;
				gNode = gNode.next;
			}
		}
		
		int n = nodeList.length;
		int loc;
		graphNode key;
		for (int i = 1 ; i < n; i++)
		{
			loc = i;
			key = nodeList[loc];
			while (loc>0 && key.compareTo(nodeList[loc-1])>0)
			{
				nodeList[loc]=nodeList[loc-1];
				loc = loc-1;
			}
			nodeList[loc] = key;
		}
		return nodeList;
	}
	
	// Checking for cycles using union find data structure
	public boolean noCycle(graphNode node)
	{
		boolean result = true;
		
		if (initial == true)
		{
			this.verticesHeadList = new int[noOfVertices];
			for (int i = 0; i < noOfVertices; i++)
			{
				verticesHeadList[i] = i;
			}
			
			this.headerChanges = new int[noOfVertices];
			for (int i = 0; i < noOfVertices; i++)
			{
				headerChanges[i] = 0;
			}
			initial = false;
		}
		
		int vertex1 = node.index;
		int vertex2 = node.nodeValue;

		if (verticesHeadList[vertex1] == verticesHeadList[vertex2])
		{
			result = false;
		}
		else
		{
			int head = node.index;
			if ((headerChanges[vertex1] != 1) && (headerChanges[vertex2] != 1))
			{
				verticesHeadList[vertex1] = head;
				verticesHeadList[vertex2] = head;
				
				headerChanges[vertex1] = 1;
				headerChanges[vertex2] = 1;
			}
			else if ((headerChanges[vertex1] == 1) && (headerChanges[vertex2] != 1))
			{
				verticesHeadList[vertex2] = verticesHeadList[vertex1];
				headerChanges[vertex2] = 1;
			}
			else if ((headerChanges[vertex1] != 1) && (headerChanges[vertex2] == 1))
			{
				verticesHeadList[vertex1] = verticesHeadList[vertex2];
				headerChanges[vertex1] = 1;
			}
			else
			{
				int preHead = verticesHeadList[vertex1];
				int indx = 0;
				for (int vertex : verticesHeadList)
				{
					if (vertex == preHead)
					{
						verticesHeadList[indx] = verticesHeadList[vertex2];
					}
					indx++;
				}
				
			}
			
		}
		return result; 
	}
	
	// Creating the minimum spanning tree
	public ArrayList<graphNode> createTree()
	{
		
		ArrayList<graphNode> treeNodes = new ArrayList<graphNode>();
		graphNode[] sortedArray = this.sortAdjacentList();
		for (graphNode node : sortedArray)
		{
			if (noCycle(node))
			{
				treeNodes.add(node);
			}
		} 
		return treeNodes;
	}
	
	// printing the adjacency linked list of the graph in the terminal
	public void printGraph()
	{
		System.out.println( "Printing the Adjacency linked list of the graph... \n");
		int start = 0;
		for (graphNode node : this.adjacencyLinkedList)
		{
			System.out.print(start+1);
			while (node != null)
			{
				System.out.print("  ===> " + (node.nodeValue+1) + "|" + node.weight + "|");
				node = node.next;
			}
			start++;
			System.out.println();
		}
		
		System.out.println("------------------------------------------------------------------------\n"); 
		
	}
	
	// printing the tree in the terminal
	public void printTree()
	{
		ArrayList<graphNode> treeNodes = this.createTree();
		
		System.out.println( "Printing the minimum spanning tree of the graph... \n");
		int start = 0, sum = 0;
		System.out.println( "node1" + "  ----   " + "node2" + "  ----  weight  \n");
		for (graphNode node : this.adjacencyLinkedList)
		{
			while (node != null)
			{
				if (treeNodes.indexOf(node) > -1)
				{
					System.out.println( "  " + (start+1) + "    ===>     " + (node.nodeValue+1) + "           |" + node.weight + "|");
					sum += node.weight;
				}
				node = node.next;
			}
			start++;
			
		}
		
		System.out.println("\nTotal cost(length) of minimum spanning tree: " + sum);
		System.out.println("------------------------------------------------------------------------\n");
		
		/*
		System.out.println("\n\n\n"); 
		for (int i = 0; i < treeNodes.size(); i++)
		{
			graphNode tmpNode = treeNodes.get(i);
			System.out.println(tmpNode.weight); 
		}
		*/
		
	}
	
	
	
	
	public static void main(String args[])
	{
		
		// Input graph is given as a text file
		// First line contains the graph ex: G(V,E) as V E
		// next lines contains the edges one edge at a line
		// Edge contatin of 2 nodes and the weight
		/*
			Text file for the current input.txt :
			
			9 28                     // G V
			1 2 3					 // v1 v2 w
			1 7 4
			2 1 3
			2 3 5
			2 7 2
			2 8 3
			3 2 5
			3 4 4
			3 8 3
			4 3 4
			4 5 3
			4 8 4
			5 4 3
			5 9 2
			6 7 2
			6 9 4
			7 1 4
			7 2 2
			7 6 2
			7 9 6
			8 2 3
			8 3 3
			8 4 4
			8 9 5
			9 5 2
			9 6 4
			9 7 6
			9 8 5
		*/
		Kruskal k1 = new Kruskal();
		k1.createAdjacencyList("graph.txt");
		k1.printGraph();
		k1.printTree();
		
		
		
	}
}
