#ifndef __Animal_H__
#define __Animal_H__
#include <iostream>

using namespace std;

class Animal{
  public:
  Animal();
  ~Animal();
  void eat();
  void move();
};

Animal::Animal(){cout<<"born."<<endl;}
void Animal::eat(){cout<<"eat."<<endl;}
void Animal::move(){cout<<"move."<<endl;}
Animal::~Animal(){cout<<"die."<<endl;}
#endif
