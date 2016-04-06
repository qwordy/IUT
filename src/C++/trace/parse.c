#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAXLEN 1024

int main() {
  char buf[MAXLEN], *p;
  double rate;

  while (fgets(buf, MAXLEN, stdin)) {
    if (strstr(buf, "Function")) {
      printf("%.*s\n", (int)(strlen(buf) - 12), buf + 10);
      fgets(buf, MAXLEN, stdin);
      p = strchr(buf, '%');
      *p = 0;
      rate = atof(buf + 15);
      printf("%d\n\n", rate == 0 ? 0 : 1);
    }
  }

  return 0;
}
