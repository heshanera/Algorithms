#include <iostream>

int fib(int);
int calcfib(int);

int main(){
	
	
	std::cout<<"\n\n\n\n"<<fib(40)<<"\n\n\n\n";
	return 0;
	}


int fib(int n){
	int ans = calcfib(n);
	return ans;
	}

int calcfib(int n){
	if (n == 0){
		return 0;
		}
	else if (n == 1){
		return 1;
		}
	else{
		return calcfib(n-1) + calcfib(n-2);; 
		}	
	}

