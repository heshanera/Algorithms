import java.util.*;
import java.io.*;

public class BellmanFord
{
	int noOfVertices;
	int noOfEdges;
	graphNode adjacencyLinkedList[];
	int pathWeightList[];
	int path[];
	
	class graphNode
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
	
	public void printGraph()
	{
		System.out.println( "Printing the Adjacency linked list of the Graph... \n");
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
	
	public void shortestPath(int s)
	{
		int[] lengthList = new int[noOfVertices];
		int[] prevNode = new int[noOfVertices];
		
		int j = 0;
		for (int tmp : lengthList)
		{
			lengthList[j] = Integer.MAX_VALUE; //Integer.MAX_VALUE;
			j++;
		}
		
		
		lengthList[s-1] = 0;
		
		for (int p = 0; p < noOfVertices-1; p++)
		{
			for (int i = 0; i < noOfVertices; i++)
			{
				graphNode currentNode = adjacencyLinkedList[i];
				graphNode tmpNode = currentNode;
				while (currentNode != null)
				{
					int index = currentNode.nodeValue;
					if ((lengthList[i] + currentNode.weight) < lengthList[index])
					{
						lengthList[index] = (lengthList[i] + currentNode.weight);
						prevNode[index] = i;	
					}
					currentNode = currentNode.next;	
				}
			}
			
			/*
			for (int a: lengthList)
			{
				System.out.print(a + " ");
			}
			System.out.println();
			
			for (int a: prevNode)
			{
				System.out.print(a + " ");
			}
			System.out.println();
			System.out.println("***********************");
			* 
			*/ 
		}
		pathWeightList  = lengthList;
		path =  prevNode;
	}
	
	public void showPath()
	{
		System.out.println("  path \t\t\t       length\n");
		int i = 0, j = 0;
		for (int distance : pathWeightList)
		{
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			i = j;	
			while (true)
			{
				tmp.add(i);
				i = path[i];
				if (i == 0)
				{
					tmp.add(0);
					break;
				}
			}
			j++;
			
			//System.out.print(tmp);
			//System.out.println(distance + " ");
			
			int q = 0;
			for (int size = tmp.size(); size > 0; size--)
			{
				System.out.print((Integer)tmp.get(size-1) + 1);
				if ((size -1) > 0)
					System.out.print(" ==> ");
				q++;	
			}
			
			int remain = 6 - q;
			for (int p = 0 ; p < remain; p++)
			{
				System.out.print('\t');
			}
			
			System.out.printf("%02d \n",distance);
		}
		System.out.println();
	}
	
	public static void main(String args[])
	{
		
		// Input graph is given as a text file
		// First line contains the graph ex: G(V,E) as V E
		// next lines contains the edges one edge at a line
		// Edge contatin of 2 nodes and the weight
		/*
			Text file for the current input.txt :
			
			6 8		V E
			1 2 9	v1 v2 w
			1 3 7
			1 5 6
			1 6 8
			2 3 -3
			3 5 -3
			4 3 -1
			6 4 -2
		*/
		BellmanFord b1 = new BellmanFord();
		b1.createAdjacencyList("BellmanFordInputGraph.txt");
		b1.printGraph();
		b1.shortestPath(1);
		b1.showPath();
	}
}
