#include <stdio.h>
#include "fly.h"
namespace fy {
int global() {puts("/home/yfy/iut/src/C++/copy of test/fly.cpp: fy.global()");
  return 1;
}

Fly::Fly() {puts("/home/yfy/iut/src/C++/copy of test/fly.cpp: fy.Fly::Fly()");
  prepare();
}

void Fly::prepare() {puts("/home/yfy/iut/src/C++/copy of test/fly.cpp: fy.Fly::prepare()");
  for (int i = 0; i < 5; i++)
    global();
}

int Fly::hehe() {puts("/home/yfy/iut/src/C++/copy of test/fly.cpp: fy.Fly::hehe()");
}
}
