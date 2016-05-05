#include <iostream>

int prefix_func(std::string, int *);
int KMP_ptrn_match(std::string, std::string, int *);

int main()
{
	
	std::string txt,ptrn;
	std::cout<<"Enter the Text: ";
	std::cin>>txt;
	std::cout<<"Enter the pattern want to match: ";
	std::cin>>ptrn;
	int ptrnlen = ptrn.length();
	int prfx[ptrnlen];
	prefix_func(ptrn,prfx);
	KMP_ptrn_match(txt,ptrn,prfx);
	return 0;	
}

int prefix_func(std::string ptrn, int * prfx)
{
	int j = 0 ,pos = 1;
	int ptrnlen = ptrn.length();
	prfx[0] = 0;
	while (pos < ptrnlen)
	{
		
		while ((j > 0) &&  (ptrn[j] != ptrn[pos]))
		{
			j = prfx[j-1];
		}
		if ( ptrn[j] == ptrn[pos] )
			{
				j++;
			}
		prfx[pos] = j;
		pos++;
	}

	return 0;
}

int KMP_ptrn_match(std::string txt, std::string ptrn, int * prfx)
{
	bool match_found = false;
	int txtlen, ptrnlen, i, j;
	txtlen = txt.length();
	ptrnlen = ptrn.length();
	while (i < txtlen)
	{
		while ((j > 0) && (ptrn[j] != txt[i]))
		{
			
			j = prfx[j];
		}
		if (ptrn[j] == txt[i])
		{
			j++;
		}
		if (j == ptrnlen)
		{
			std::cout<<"match found from "<< i-ptrnlen + 1 <<" to "<<i<<"\n";
			j = prfx[j];
			match_found = true;
		}
		i++;
	}
	if (match_found == false)
	{
		std::cout<<"NO match found!!!"; 
	}
	return 0;
}
