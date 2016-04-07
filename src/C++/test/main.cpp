#include <iostream>
#include "fly.h"
#include "d/hd.h"
#include "d/book.h"
using namespace std;

class Foo {
public:
  void foo() {
    //cout << __FILE__ << endl;
    //cout << __LINE__ << endl;
    //cout << __FUNCTION__ << endl;
    foo(1);
  }

  inline void foo(int x) {
    //cout << __FILE__ << endl;
    //cout << __LINE__ << endl;
    //cout << __FUNCTION__ << endl;
  }
};

class Foo2 {
public:
  void foo() {
    //cout << __FILE__ << endl;
    //cout << __LINE__ << endl;
    //cout << __FUNCTION__ << endl;
  }
};

int main() {
  Foo foo;
  foo.foo();
  foo.foo(1);
  Foo2 foo2;
  foo2.foo();
  Fly fly;
  add(1, 2);
  Book book;
  return 0;
}

