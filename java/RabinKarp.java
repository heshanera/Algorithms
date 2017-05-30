import java.util.*;

public class RabinKarp
{	
	public static long calcPatternHash(String def)
	{
		int length = def.length();
		long sVal = 0;
		for (int i = 0; i < length; i++)
		{
			sVal = ((int)(def.charAt(i)) + sVal)*3;
		}
		return sVal;
	}
	
	public static boolean match_deep(String p1, String p2)
	{
		int l = p1.length();
		boolean match = true;
		for (int i = 0; i < l; i++)
		{
			if (p1.charAt(i) != p2.charAt(i))
			{
				match = false;
				break;
			}
		}
		return match;
	}
	
	public static void match(String txt, String ptrn)
	{
		
		long patternHash = calcPatternHash(ptrn);
		long subStringHash = 0;
		int patternLength = ptrn.length();
		int textLength = txt.length();
		
		int end = patternLength - 1;
		
		for (int i = 0 ; i < (textLength-patternLength + 1); i++,end++)
		{
			String subString = txt.substring(i,end+1);
			subStringHash = calcPatternHash(subString);
			if (subStringHash == patternHash)
			{
				boolean matching = match_deep(subString, ptrn);
				if (matching)
				{
					System.out.println("Match found from " + i + " to " + end);
					i += (patternLength - 1);
					end += (patternLength - 1);
				}
			}
			
		}				 
	}
	
	
	public static void matchOverlaping(String txt, String ptrn)
	{
		
		long patternHash = calcPatternHash(ptrn);
		long subStringHash = 0;
		int patternLength = ptrn.length();
		int textLength = txt.length();
		
		int end = patternLength - 1;
		
		for (int i = 0 ; i < (textLength-patternLength + 1); i++,end++)
		{
			String subString = txt.substring(i,end+1);
			subStringHash = calcPatternHash(subString);
			if (subStringHash == patternHash)
			{
				boolean matching = match_deep(subString, ptrn);
				if (matching)
				{
					System.out.println("Match found from " + i + " to " + end);
				}
			}
			
		}				 
	}
	
	
	/*
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		String[] inputArray = input.split(" ");
		String text = inputArray[0];
		String pattern = inputArray[1];
		RabinCarp.matchOverlaping(text,pattern);	
	}
	*/
	
}	
