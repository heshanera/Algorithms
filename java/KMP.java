import java.util.*;

public class KMP
{
	public static int[] prefix_func(String ptrn)
	{
		int j = 0 ,pos = 1;
		int ptrnlen = ptrn.length();
		int[] prfx = new int[ptrnlen];
		prfx[0] = 0;
		while (pos < ptrnlen)
		{
			
			while ((j > 0) &&  (ptrn.charAt(j) != ptrn.charAt(pos)))
			{
				j = prfx[j-1];
			}
			if ( ptrn.charAt(j) == ptrn.charAt(pos) )
			{
				j++;
			}
			prfx[pos] = j;
			pos++;
		}
	
		/*
		System.out.println("*** prefix :");
		for (int a : prfx)
		{
			System.out.print(a + " ");
		}
		System.out.println("\n\n");
		*/
		return prfx;
	}
	
	public static int match(String txt, String ptrn)
	{
		int[] prfx = prefix_func(ptrn);
		boolean match_found = false;
		int txtlen, ptrnlen, i = 0, j = 0;
		txtlen = txt.length();
		ptrnlen = ptrn.length();
		while (i < txtlen)
		{
			while ((j > 0) && (ptrn.charAt(j) != txt.charAt(i)))
			{
				j = prfx[j-1];
			}
			if (ptrn.charAt(j) == txt.charAt(i))
			{
				j++;
			}
			if (j == ptrnlen)
			{
				System.out.println("match found from " +  (i-ptrnlen+1) + " to " + i);
				j = 0;
				match_found = true;
			}
			i++;
		}
		if (match_found == false)
		{
			System.out.println("NO match found!!!"); 
		}
		return 0;
	}
	
	public static int matchOverlaping(String txt, String ptrn)
	{
		int[] prfx = prefix_func(ptrn);
		boolean match_found = false;
		int txtlen, ptrnlen, i = 0, j = 0;
		txtlen = txt.length();
		ptrnlen = ptrn.length();
		while (i < txtlen)
		{
			while ((j > 0) && (ptrn.charAt(j) != txt.charAt(i)))
			{
				
				j = prfx[j-1];
			}
			if (ptrn.charAt(j) == txt.charAt(i))
			{
				j++;
			}
			if (j == ptrnlen)
			{
				System.out.println("match found from " +  (i-ptrnlen+1) + " to " + i);
				j = 0;
				i = i - ptrnlen + 1;
				match_found = true;
			}
			i++;
		}
		if (match_found == false)
		{
			System.out.println("NO match found!!!"); 
		}
		return 0;
	}
	
	/*
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		String[] inputArray = input.split(" ");
		String text = inputArray[0];
		String pattern = inputArray[1];
		KMP.match(text,pattern);	
	}
	*/
	
}
