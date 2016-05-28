#include "fly.h"

int global() {
  return 1;
}

Fly::Fly() {
  prepare();
}

void Fly::prepare() {
  for (int i = 0; i < 5; i++)
    global();
}

int Fly::hehe() {
}

