### T - largrt text ### P - pattern to match

def BruteForceMatch(T,P):
	i = 0			
	for i in range(len(T)-len(P)+1):	
		j = 0
		for j in range(len(P)):
			if T[i+j] != P[j]:			
				break
			else:
				if j == len(P)-1:
					print "match is found at" , i+1 ,"to ", i+j+1 ,"(letter no of the text)"
					i += j
			

txt = raw_input("Enter the larger text: ")
ptrn = raw_input("Enter the pattern want to match: ")
BruteForceMatch(txt,ptrn)
