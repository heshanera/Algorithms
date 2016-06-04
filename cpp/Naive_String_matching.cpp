#include <iostream>
#include <string.h>


int BruteForceMatch(std::string,std::string);

int main()
{
	std::string txt,ptrn;
	std::cout<<"Enter the Text: ";
	std::cin>>txt;
	std::cout<<"Enter the pattern want to match: ";
	std::cin>>ptrn;
	BruteForceMatch(txt,ptrn);
	
	return 0;
}

int BruteForceMatch(std::string txt,std::string ptrn)
{
	int i = 0,j,txtlen,ptrnlen;
	txtlen = txt.length();
	ptrnlen = ptrn.length();
	
	while (i < txtlen)
	{
		j = 0;
		while (j < ptrnlen)
		{
			if (txt[i+j] != ptrn[j])
			{
				break;
			}
			else
			{
				if ((txt[i+j] == ptrn[j]) && (j == (ptrnlen-1)))
				{
					std::cout<<"match found from "<<i+1<<" to "<<i+j+1<<'\n'; 
					i += j+1;
					j = 0;
				}
			}
			j++;
		}
		i++;
	}
	return 0;
}
