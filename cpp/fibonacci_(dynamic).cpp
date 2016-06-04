#include <iostream>

int fib(int);
int calcfib(int, int *);

int main(){
	
	
	std::cout<<"\n\n\n\n"<<fib(40)<<"\n\n\n\n";
	return 0;
	}


int fib(int n){
	int tmp[n+1];
	tmp[0] = 0;
	tmp[1] = 1;
	for (int i=2; i < n+1; i++){
		tmp[i] = -1;
		}
	int ans = calcfib(n,tmp);
	return ans;
	}

int calcfib(int n, int *p){
	if (n == 0){
		return p[0];
		}
	else if (n == 1){
		return p[1];
		}
	else{
		int tmp;
		if (p[n] != -1)
			tmp = p[n];
		else{
			tmp = calcfib(n-1,p) + calcfib(n-2,p);
			p[n] = tmp;
			}
		return tmp; 
		}	
	}
