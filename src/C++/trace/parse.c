#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <dirent.h>
#include <err.h>

#define MAXLEN 1024

void aparse(char *file) {
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
}

void parse(char *file) {
  char buf[MAXLEN], funcname[MAXLEN], *result, *pos;
  FILE *f;
  int times, line;

  printf("parse %s\n", file);
  printf("line No. | function name | called times\n");
  f = fopen(file, "r");
  result = fgets(buf, MAXLEN, f);
  while (result) {
    if (sscanf(buf, "function %s called %d", funcname, &times) == 2) {
      if ((result = fgets(buf, MAXLEN, f)) && (pos = strchr(buf, ':')) &&
          sscanf(pos + 1, "%d", &line) == 1) {
        printf("%d ", line);
        result = fgets(buf, MAXLEN, f);
      } else {
        printf("? ");
      }
      printf("%s %d\n", funcname, times);
    } else {
      result = fgets(buf, MAXLEN, f);
    }
  }

}

int isgcov(char *file) {
  char *pos = strrchr(file, '.');
  return pos && !strcmp(pos + 1, "gcov");
}

void ls(char *path) {
  DIR *dir;
  struct dirent *ent;
  char buf[MAXLEN];
  int len;

  if ((dir = opendir(path)) == NULL)
    err(-1, "can't open %s", path);

  strncpy(buf, path, MAXLEN);
  strcat(buf, "/");
  len = strlen(buf);
  while ((ent = readdir(dir))) {
    if (ent->d_type == 8 && isgcov(ent->d_name)) {
      buf[len] = 0;
      parse(strcat(buf, ent->d_name));
    }
  }
}

int main(int argc, char **argv) {
  ls(argv[1]);
  return 0;
}

