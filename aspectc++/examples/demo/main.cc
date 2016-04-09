#include <iostream>
#include "animal.h"

using namespace std;

int main(){
  Animal cat;
  Animal dog;

  cat.eat();
  dog.move();

  cat.~Animal();
  
  return 0;
}
