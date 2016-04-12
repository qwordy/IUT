#include <iostream>
#include "animal.h"
#include "cat.h"
using namespace std;
void print_hello_world(int, int);
inline int add(int, int);

void print_hello_world(int a, int b){
  cout<<"Hello World.\n"<<__FILE__<<endl;
  cout<<"a + b = "<<add(a, b)<<endl;
}

inline int add(int a, int b){
  
  return a+b;
}

int main(){
  Cat cat;
  Animal dog;

  cat.eat();
  dog.move();
  cat.say();
  int sum;
  sum =  add(2, 3);
  print_hello_world(2, 3);
  
  return 0;
}
