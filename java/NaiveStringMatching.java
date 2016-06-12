import java.util.*;

public class NaiveStringMatching
{
	public static void match(String text, String pattern)
	{
		char[] Text = text.toCharArray();
		char[] Pattern = pattern.toCharArray();
		
		
		int i = 0,j,txtlen,ptrnlen;
		txtlen = text.length();
		ptrnlen = pattern.length();
		int[] patternCor = new int[2];
		
		
		while (i < txtlen)
		{	
			try
			{
				j = 0;
				while (j < ptrnlen)
				{
					if (Text[i+j] != Pattern[j])
					{
						break;
					}
					else
					{
						if ((Text[i+j] == Pattern[j]) && (j == (ptrnlen-1)))
						{
							// printing the pattern the 2 index values starting from 0
							System.out.println("match found from " + (i) + " to "  + (i+j));
							// storing the current pattern with indexs
							patternCor[0] = i;
							patternCor[1] = i+j;  
							i += j+1;
							j = 0;
						}
					}
					j++;
				}
				i++;
			}
			catch(Exception e)
			{
				i++;
			}	
		}	
	}
	
	
	
	
	public static void matchOverlaping(String text, String pattern)
	{
		char[] Text = text.toCharArray();
		char[] Pattern = pattern.toCharArray();
		
		
		int i = 0,j,txtlen,ptrnlen;
		txtlen = text.length();
		ptrnlen = pattern.length();
		int[] patternCor = new int[2];
		
		
		while (i < txtlen)
		{	
			try
			{
				j = 0;
				while (j < ptrnlen)
				{
					if (Text[i+j] != Pattern[j])
					{
						break;
					}
					else
					{
						if ((Text[i+j] == Pattern[j]) && (j == (ptrnlen-1)))
						{
							// printing the pattern the 2 index values starting from 0
							System.out.println("match found from " + (i) + " to "  + (i+j));
							// storing the current pattern with indexs
							patternCor[0] = i;
							patternCor[1] = i+j;  
							j = 0;
							i++;
						}
					}
					j++;
				}
				i++;
			}
			catch(Exception e)
			{
				i++;
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
		NaiveStringMatching.matchOverlaping(text,pattern);	
	}
	*/

}
