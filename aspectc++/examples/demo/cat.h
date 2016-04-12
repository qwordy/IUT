#ifndef __CAT_H__
#define __CAT_H__

#include "animal.h"

class Cat : public Animal{
public:
void say();  

};

void Cat::say(){
  cout<<"Meow Meow"<<endl;
}



#endif
