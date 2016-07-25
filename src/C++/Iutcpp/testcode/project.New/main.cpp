#include <iostream>
#include <string>
#define PI 3.14
#define _P(x) x
using namespace std;


int a = 10;

class Animal{

	public:
		string name;
		string getName(string kind);
};

string Animal::getName(string kind){
	return name+" is a/an "+kind;
}

bool biggerThan (int a, int b = 0){
		return a > b;
	}
void foo(Animal ani){
	cout<<"Name: "<<ani.name<<endl;
}

int minus (int x, int y){
	return x-y;
}

void a(char b){

}

int AddThreeInt(int iFirst, int iSecond, int iThree)  
{  
    struct AddTwoInt  
    {  
        int operator()(int iOne, int iTwo)  
        {  
            return iOne + iTwo;  
        }  
    } AddTwoInt;  
    return AddTwoInt(iFirst, iSecond) + iThree;  
}  

int main(){
	int i = 0;
	for(i = 0; i<10;i++){
		cout<<"i: "<<i<<endl;
	}
	cout<<_P(i)<<endl;
	return 0;
	
	
}
