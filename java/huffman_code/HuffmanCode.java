import java.io.*;
import java.util.*;

public class HuffmanCode
{
	ArrayList<ArrayList<Object>> occuranceNumber = new ArrayList<ArrayList<Object>>();
	ArrayList<Object> code = new ArrayList<Object>();
	
	ArrayList<Object> originalChars;
	ArrayList<Object> originalOcurences;
	
	int noOfseparateChars;
	
	
	// Creating a class for the node of the huffmancode tree 
	class Node
	{
		int frequencySum = -1;
		char character;
		Node left;
		Node right;
		
		public Node()
		{

		}
		
		public Node(int freq)
		{
			this.frequencySum = freq;
		}
		
		public Node(char character)
		{
			this.character = character;
		}
		
	}
	
	
	//Sorting the array according the occurances of characters
	public void sort()
	{
		ArrayList<Object> chars = occuranceNumber.get(0);
		ArrayList<Object> values = occuranceNumber.get(1);
		int tmp,min,minIndex;
		int length = values.size();
		for (int i = 0; i < length; i++)
		{
			min = (int)values.get(i);
			minIndex = i;
			for (int j = i; j < length; j++)
			{
				tmp = (int)values.get(j);
				if (tmp < min)
				{
					min = tmp;
					minIndex = j;
				}
			}
			values.remove(minIndex);
			values.add(i,min);
			Object obj1 = chars.remove(minIndex);
			if (obj1.getClass().equals(Character.class))
			{
				chars.add(i,(char)obj1);
			}
			else if (obj1.getClass().equals(Integer.class)) 
			{
				chars.add(i,(int)obj1);
			}
			else 
			{
				chars.add(i,obj1);
			}
		}
		
	}
	
	// getting the input file in to a character separates array
	public ArrayList<ArrayList<Object>> separateChars(String fileName)
	{
		char[] charLine;
		int index;
		ArrayList<Object> character = new ArrayList<Object>();
		ArrayList<Object> occurance = new ArrayList<Object>();
		try
		{	
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String s;
			int lines = 0;
			while ((s = br.readLine()) != null)
			{
				s = s.toLowerCase();
				charLine = s.toCharArray();	
				// seperating characters
				for (char c :charLine)
				{
					index = character.indexOf(c);
					if (index == -1)
					{
						character.add(c);
						index = character.indexOf(c);
						occurance.add(index,1);
					}
					else
					{
						int tmpVal = (int)occurance.remove(index);
						tmpVal += 1;
						occurance.add(index,tmpVal);
					}
				}	
				lines++;				
			}
			char c = '\n';
			character.add(c);
			index = character.indexOf(c);
			occurance.add(index,lines-1);
			
			br.close();
			fr.close();
		}
		catch(IOException ex){
			System.out.println("ERROR IN FILE..!!!");
		}
		noOfseparateChars = character.size();
		occuranceNumber.add(character);
		occuranceNumber.add(occurance);
		
		originalChars = new ArrayList<Object>(character);
		originalOcurences = new ArrayList<Object>(occurance);
		
		return occuranceNumber;
	}
	
	
	
	public Node caluculateHuffmanCode(ArrayList<ArrayList<Object>> occurances)
	{
		Object obj1, obj2;
		Node root = new Node();
		int sum = 0, length, newFrequency;
		
		ArrayList<Object> chars = occurances.get(0);
		ArrayList<Object> values = occurances.get(1);
		
		length = values.size();
		
		
		for (int i = 0; i < length; i++)
		{
			sum += (int)values.get(i);
		}
			
		while (true)
		{
			Node tmpRoot = new Node();
			this.sort();
			obj1 = chars.remove(0);
			obj2 = chars.remove(0);
			if (obj1.getClass().equals(Character.class))  
				tmpRoot.left = new Node((char)obj1);
			else if (obj1.getClass().equals(Integer.class)) 
				tmpRoot.left = new Node((int)obj1);
			else
				tmpRoot.left = (Node)obj1;
				
			if (obj2.getClass().equals(Character.class))  
				tmpRoot.right = new Node((char)obj2);
			else if (obj1.getClass().equals(Integer.class))
				tmpRoot.right = new Node((int)obj2);
			else
				tmpRoot.right = (Node)obj2;		
					
			newFrequency = (int)values.remove(0) + (int)values.remove(0);
			tmpRoot.frequencySum = newFrequency;
			
			chars.add(0,tmpRoot);
			values.add(0,newFrequency);
			if (newFrequency == sum)
				break;
			
			/*
			getCode(tmpRoot,"");
			System.out.println("**************************");
			System.out.println(chars);
			System.out.println(values);
			System.out.println("**************************");
			*/
		}
		root = (Node)chars.get(0);
		return root;
		
	}
	
	public ArrayList<Object> getCode(Node n, String currentCode)
	{
		
		if (n.frequencySum == -1)
		{
			//System.out.println(n.character + "  =====>  " + currentCode);
			code.add(n.character);
			code.add(currentCode);
		}
		if (n.left != null)
		{
			currentCode += "0";
			getCode(n.left, currentCode);
		}
		currentCode = currentCode.substring(0,(currentCode.length()-1));
		if (n.right != null)
		{
			currentCode += "1";
			getCode(n.right, currentCode);	
		}
		return code;
	}
	
	public ArrayList<Object> sortCodes(ArrayList<Object> table)
	{
		int size = table.size(), min, minIndex, tmp;
		for (int i = 1; i < size; i+= 2)
		{
			min = ((String)table.get(i)).length();
			minIndex = i;
			for (int j = i; j < size; j += 2)
			{
				tmp = ((String)table.get(j)).length();
				if (tmp < min)
				{
					min = tmp;
					minIndex = j;
				}
			}
			Object obj1 = table.remove(minIndex);
			Object obj2 = table.remove(minIndex-1);
			table.add(i-1,obj1);
			table.add(i-1,obj2);
		}
		return table;
	}
	
	public String printOriginalFile(String fileName)
	{
		String file = "";
		try
		{	
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String s;
			char[] charLine;
			while ((s = br.readLine()) != null)
			{
				//s = s.toLowerCase();	
				System.out.println(s);
				file += s + "\n";
			}
			br.close();
			fr.close();
		}
		catch(IOException ex){
			System.out.println("ERROR IN FILE..!!!");
		}
		return file;
	}
	
	public String convertToBinary(int decimal)
	{
		String binary = "";
		while (decimal > 0)
		{
			int remain = decimal % 2;
			decimal = decimal / 2;
			binary = Integer.toString(remain) + binary; 
		}
		if (binary.length() < 8)
		{
			while (binary.length() < 8)
			{
				binary = "0" + binary; 
			}
		}	
		
		return binary;
	}
	
	public String[] normalConvertedFile(String fileName)
	{
		String file = "";
		String[] fileBits = new String[2];
		int bits = 0;
		try
		{	
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String s;
			char[] charLine;
			while ((s = br.readLine()) != null)
			{	
				s = s.toLowerCase();
				charLine = s.toCharArray();	
				// seperating characters
				for (char c :charLine)
				{
					int value = (int)c;
					String normal = convertToBinary(value);
					//System.out.print(normal);
					file += normal;
					bits += 8;
				}
				char c = '\n';
				int value = (int)c;
				String normal = convertToBinary(value);
				//System.out.print(normal);
				file += normal;
				bits += 8;	
				
			}
			
			file = file.substring(0, (file.length() - 8));
			bits -= 8;	
			
			br.close();
			fr.close();
		}
		catch(IOException ex){
			System.out.println("ERROR IN FILE..!!!");
		}
		
		fileBits[0] = Integer.toString(bits);
		fileBits[1] = file;
		return fileBits;
	}
	
	
	public String[] printConvertedFile(String fileName,ArrayList<Object> table)
	{
		String file = "";
		String[] fileBits = new String[2];
		int bits = 0 , newLineLength = 0;
		try
		{	
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String s;
			char[] charLine;
			while ((s = br.readLine()) != null)
			{
				s = s.toLowerCase();
				charLine = s.toCharArray();	
				// seperating characters
				for (char c :charLine)
				{
					int index = table.indexOf(c);
					//System.out.print(table.get(index+1));
					file += (String)table.get(index+1);
					bits += ((String)table.get(index+1)).length();
				}
				
				char c = '\n';
				int index = table.indexOf(c);
				//System.out.print(table.get(index+1));
				file += (String)table.get(index+1);
				newLineLength = ((String)table.get(index+1)).length();
				bits += newLineLength;		
			}
			
			bits -= newLineLength;
			file = file.substring(0,file.length() - newLineLength);
			
			br.close();
			fr.close();
		}
		catch(IOException ex){
			System.out.println("ERROR IN FILE..!!!");
		}
		fileBits[0] = Integer.toString(bits);
		fileBits[1] = file;
		return fileBits;
	}
	
	
	public static void main(String args[])
	{	
		String fileName = "huffmanCodeInput.txt";
		ArrayList<ArrayList<Object>> occurances = new ArrayList<ArrayList<Object>>();
		HuffmanCode h1 = new HuffmanCode();
		occurances = h1.separateChars(fileName);
		ArrayList<Object> chars = occurances.get(0);
		ArrayList<Object> values = occurances.get(1);
		
		Node root = h1.caluculateHuffmanCode(occurances);
		ArrayList<Object> table = h1.getCode(root,"");
		table = h1.sortCodes(table);

		//Start writing to an output file
		try
		{
			FileWriter fw = new FileWriter("HuffmanCodeOutput.txt");
			BufferedWriter bw = new BufferedWriter(fw);

			System.out.println("Frequency  \t  Character  \t     Code\n");			// Printing in th terminal
			bw.write("Frequency  \t  Character  \t            Code\n\n");                    // Writting in the file
			int ln = table.size();
			for(int i = 0; i < ln; i += 2)
			{
				
				Object tmpChar = table.get(i);
				int index = h1.originalChars.indexOf(tmpChar);
				tmpChar = (char)tmpChar;
				int space = (int)' ';
				int newLine = (int)'\n';
				int tmpC = (int)((char)tmpChar);
				if (tmpC == newLine)
				{
					System.out.print("    " + h1.originalOcurences.get(index) + "     =====     \\n      ====>    ");
					bw.write("    " + h1.originalOcurences.get(index) + "     =====      \\n  \t====>    ");
				}
				else if (tmpC == space)
				{
					System.out.print("   " + h1.originalOcurences.get(index) + "     =====    space    ====>    ");
					bw.write("   " + h1.originalOcurences.get(index) + "     =====    space       ====>    ");
				}
				else	
				{
					System.out.print("    " + h1.originalOcurences.get(index) + "     =====      " + table.get(i) + "      ====>    ");
					bw.write("    " + h1.originalOcurences.get(index) + "     =====       " + table.get(i) + "   \t====>    ");
					
				}
				System.out.print(table.get(i+1) + "\n");	
				bw.write(table.get(i+1) + "\n");
			}		
			
			// Printing in terminal
			System.out.println("\n\nTotal no of Characters: " + values);
			System.out.println("\n---- Original file: \n");
			String file = h1.printOriginalFile(fileName);
			System.out.println("\n\n\n---- Converted file (Fixed length - 8bits per character): \n");
			String[] normalBits = h1.normalConvertedFile(fileName);
			System.out.println(normalBits[1]);
			System.out.println("\n\n---- " + normalBits[0] + " bits ------------- \n");
			System.out.println("\n\n---- Converted file (Huffman Code): \n");
			String[] huffmanBits = h1.printConvertedFile(fileName,table);
			System.out.println(huffmanBits[1]);
			System.out.println("\n\n---- " + huffmanBits[0] + " bits ------------- ");
			
			
			// Writting in the file                  
			bw.write("\n\n\nTotal no of Characters: " + values);
			bw.write("\n\n---- Original file: \n");
			bw.write(file);
			bw.write("\n\n\n---- Converted file (Fixed length - 8bits per character): \n\n");
			bw.write(normalBits[1]);
			bw.write("\n---- " + normalBits[0] + " bits ------------- \n");
			bw.write("\n\n---- Converted file (Huffman Code): \n\n");
			bw.write(huffmanBits[1]);
			bw.write("\n---- " + huffmanBits[0] + " bits ------------- ");
		
			bw.close();
			fw.close();
		}catch(Exception e)
		{
			System.out.println("Error in the output file!");
			e.printStackTrace();
		}
	}	
}
