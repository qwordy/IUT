#include <stdio.h>
#include "fly.h"

int global() {puts("/home/yfy/iut/src/C++/test2/fly.cpp: global()");
  return 1;
}

Fly::Fly() {puts("/home/yfy/iut/src/C++/test2/fly.cpp: Fly::Fly()");
  prepare();
}

void Fly::prepare() {puts("/home/yfy/iut/src/C++/test2/fly.cpp: Fly::prepare()");
  for (int i = 0; i < 5; i++)
    global();
}

int Fly::hehe() {puts("/home/yfy/iut/src/C++/test2/fly.cpp: Fly::hehe()");
}

