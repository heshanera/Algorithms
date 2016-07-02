import java.util.*;
import java.io.*;

public class Dijkstras
{
	int noOfVertices;
	int noOfEdges;
	graphNode adjacencyLinkedList[];
	int pathWeightList[];
	ArrayList<Integer> visited;
	int path[];
	int start;
	
	class graphNode
	{
		graphNode next;
		graphNode prev;
		int nodeValue;
		int weight;
		int index;
		
		public graphNode(int val)
		{
			this.nodeValue = val;
			this.weight = 0;
			this.next = null;
		}
		
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
	
	public int findMinimum(ArrayList toVisit)
	{
		int result = 0;
		if (toVisit.size() == 1)
		{
			result = (Integer)toVisit.get(0);
		}
		else
		{
			int min, minindex;
			min = pathWeightList[(Integer)toVisit.get(0)];
			minindex = 0;
			for(int i = 0; i < toVisit.size(); i++)
			{
				int tmp = (Integer)toVisit.get(i);
				if (pathWeightList[(Integer)toVisit.get(i)] < min)
				{
					min = (Integer)toVisit.get(i);
					minindex = i;
				}
			}
			result = (Integer)toVisit.get(minindex);
		}	
		return result;
	}
	
	public void shortestPath(int start)
	{
		start--;
		start = start;
		pathWeightList = new int[noOfVertices];
		path = new int[noOfVertices];
		int prev = -1;
		visited = new ArrayList<Integer>();
		int i = 0;
		for (int tmp : pathWeightList)
		{
			pathWeightList[i] = -1;
			path[i] = -1;
			i++;
		}
		
		pathWeightList[start] = 0;
		ArrayList<Integer> toVisit = new ArrayList<Integer>();
		int visiting = start;
		
		toVisit.add(visiting);
		while (toVisit.size() > 0)
		{
			int visitNode = findMinimum(toVisit);
			visited.add(visitNode);
			int tmp = toVisit.indexOf(visitNode);
			//System.out.println(visitNode);
			//System.out.println(tmp + "*****************");
			toVisit.remove(tmp);
			
			graphNode visitingNode = adjacencyLinkedList[visitNode];
			graphNode tmpNode = visitingNode;
			while (visitingNode != null)
			{
				if (visited.indexOf(visitingNode.nodeValue) == -1)
				{
					if (toVisit.indexOf(visitingNode.nodeValue) == -1)
					{
						toVisit.add(visitingNode.nodeValue);
					}
				}
				if (pathWeightList[visitingNode.nodeValue] == -1)
				{
					pathWeightList[visitingNode.nodeValue] = visitingNode.weight + pathWeightList[visitNode];
					int tmpVal = visitingNode.weight + pathWeightList[visitNode];
					path[visitingNode.nodeValue] = visitNode;
				}	
				else
				{
					int currentValue = pathWeightList[visitingNode.nodeValue];
					int newValue = visitingNode.weight + pathWeightList[visitNode];
					//System.out.println(visitingNode.weight  + "  ---------***************----------  " + pathWeightList[visitNode]);
					//System.out.println(currentValue  + "  -------------------  " + newValue);
					if (newValue < currentValue)
					{
						pathWeightList[visitingNode.nodeValue] = newValue;
						path[visitingNode.nodeValue] = visitNode;
						
					}
				}
				visitingNode = visitingNode.next;
			}
			prev = visitNode;
			//System.out.println("\n" + toVisit);
		}
	}
	
	
	public void printShortestPaths()
	{
		System.out.println("Shortest Distance: \n");
		char[] tmp = {'A','B','C','D','E','F','G','H'};
		
		
		System.out.println("  path \t\t\t     length\n");
		int i = 0, j = 0;
		for (int distance : pathWeightList)
		{
			ArrayList<Integer> tmpArray = new ArrayList<Integer>();
			i = j;	
			while (true)
			{
				tmpArray.add(i);
				i = path[i];
				if (i == -1)
				{
					tmpArray.add(0);
					break;
				}
			}
			j++;
			
			//System.out.print(tmp);
			//System.out.println(distance + " ");
			if (distance != -1)
			{
				int q = 0;
				for (int size = tmpArray.size()-1; size > 0; size--)
				{
					System.out.print(tmp[(Integer)tmpArray.get(size-1)]);
					if ((size -1) > 0)
						System.out.print(" ==> ");
					q++;	
				}
				
				int remain = 6 - q;
				for (int p = 0 ; p < remain; p++)
				{
					System.out.print("      ");
				}
				
				System.out.printf("%02d\n",distance);
			}
			else
				System.out.println("\n*** " + tmp[j-1] + " Cannot be reached from " + tmp[start]);
			
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
			
			8 14		V E
			1 2 2		v1 v2 w
			1 6 8
			1 7 9
			2 3 1
			3 4 5
			3 6 4
			4 3 1
			4 5 2
			4 6 1
			6 4 1
			6 7 2
			7 1 2
			8 2 5
			8 7 3

		*/
		Dijkstras d1 = new Dijkstras();
		d1.createAdjacencyList("DijkstrasGraphInput.txt");
		d1.printGraph();
		d1.shortestPath(1);
		d1.printShortestPaths();
		
		
		
		
		
	}
}
