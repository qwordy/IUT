#include <stdio.h>
#include <iostream>
#include "fly.h"
#include "d/hd.h"
#include "d/book.h"
using namespace std;

class Foo {
public:
  void foo() {puts("/home/yfy/iut/src/C++/copy of test/main.cpp: Foo::Foo::foo()");
    //cout << __FILE__ << endl;
    //cout << __LINE__ << endl;
    //cout << __FUNCTION__ << endl;
    foo(1);
  }

  inline void foo(int x) {puts("/home/yfy/iut/src/C++/copy of test/main.cpp: Foo::Foo::foo(int x)");
    //cout << __FILE__ << endl;
    //cout << __LINE__ << endl;
    //cout << __FUNCTION__ << endl;
  }
};

class Foo2 {
public:
  void foo() {puts("/home/yfy/iut/src/C++/copy of test/main.cpp: Foo2::Foo2::foo()");
    //cout << __FILE__ << endl;
    //cout << __LINE__ << endl;
    //cout << __FUNCTION__ << endl;
  }
};

int main() {puts("/home/yfy/iut/src/C++/copy of test/main.cpp: main()");
  Foo foo;
  foo.foo();
  foo.foo(1);
  Foo2 foo2;
  foo2.foo();
  fy::Fly fly;
  add(1, 2);
  Book book;
  return 0;
}

