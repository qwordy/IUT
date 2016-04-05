#include <iostream>
using namespace std;

class Foo {
public:
  void foo() {
    cout << __FILE__ << endl;
    cout << __LINE__ << endl;
    cout << __FUNCTION__ << endl;
  }

  void foo(int x) {
    cout << __FILE__ << endl;
    cout << __LINE__ << endl;
    cout << __FUNCTION__ << endl;
  }
};

class Foo2 {
public:
  void foo() {
    cout << __FILE__ << endl;
    cout << __LINE__ << endl;
    cout << __FUNCTION__ << endl;
  }
};

int main() {
  Foo foo;
  foo.foo();
  foo.foo(1);
  Foo2 foo2;
  foo2.foo();
  return 0;
}

