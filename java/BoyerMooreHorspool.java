import java.util.*;

public class BoyerMooreHorspool
{
	
	public static ArrayList preProcess(String pattern)
	{
		int len = pattern.length();
		
		ArrayList<Integer> tmpArray = new ArrayList<Integer>();
		ArrayList<Character> added = new ArrayList<Character>();
		
		added.add(0,'*');
		tmpArray.add(0,len);
		
		for (int i = 2 ; i < len+1 ; i++)
		{
			char c1 = pattern.charAt(len-i);
			int result  = added.indexOf(c1);
			if (result == -1)
				added.add(0,c1);tmpArray.add(0,i-1); 
		}

		ArrayList<ArrayList> newArray = new ArrayList<ArrayList>();
		newArray.add(0,added);
		newArray.add(1,tmpArray);
		return newArray;
	}
	
	public static void match(String text, String pattern)
	{
		ArrayList tmp = preProcess(pattern);
		
		ArrayList charList = (ArrayList)tmp.get(0);
		ArrayList posList = (ArrayList)tmp.get(1);

		
		int patternLen = pattern.length();
		int textLen = text.length();
		int tmplen = patternLen - 1; 
		
		int i = tmplen;
		while (i < textLen)
		{
			int j;
			for (j = 0; j < patternLen; j++)
			{
				if (text.charAt(i -j) != pattern.charAt(tmplen - j))
				{
					char c = text.charAt(i -j);
					int result  = charList.indexOf(c);
					if (c == -1)
					{
						i += patternLen;
						break;
					}
					else
					{
						Integer intObj = (Integer)posList.get(result);
						int tmpIndx = intObj.intValue();
						i += tmpIndx;
						break;
					}
					
				}
				else if (j == tmplen)
				{
					System.out.println("Pattern found from " + (i - j) + " to " + i);	
					i += patternLen;
				}
			}	
		}
		
	}
	
	public static void matchOverlaping(String text, String pattern)
	{
		ArrayList tmp = preProcess(pattern);
		
		ArrayList charList = (ArrayList)tmp.get(0);
		ArrayList posList = (ArrayList)tmp.get(1);

		
		int patternLen = pattern.length();
		int textLen = text.length();
		int tmplen = patternLen - 1; 
		
		int i = tmplen;
		while (i < textLen)
		{
			int j;
			for (j = 0; j < patternLen; j++)
			{
				if (text.charAt(i -j) != pattern.charAt(tmplen - j))
				{
					char c = text.charAt(i -j);
					int result  = charList.indexOf(c);
					if (c == -1)
					{
						i += patternLen;
						break;
					}
					else
					{
						Integer intObj = (Integer)posList.get(result);
						int tmpIndx = intObj.intValue();
						i += tmpIndx;
						break;
					}
					
				}
				else if (j == tmplen)
				{
					System.out.println("Pattern found from " + (i - j) + " to " + i);	
					i++;
				}
			}	
		}
		
	}
}
