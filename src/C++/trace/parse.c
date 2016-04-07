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
  printf("%s\n", file);
}

int isgcov(char *file) {
  char *pos = strrchr(file, '.');
  return pos && !strcmp(pos + 1, "gcov");
}

void ls(char *path) {
  DIR *dir;
  struct dirent *ent;
  char buf[MAXLEN];

  if ((dir = opendir(path)) == NULL)
    err(-1, "can't open %s", path);

  strncpy(buf, path, MAXLEN);
  while ((ent = readdir(dir))) {
    if (ent->d_type == 8 && isgcov(ent->d_name))
      parse(strcat(buf, ent->d_name));
  }
}

int main(int argc, char **argv) {
  ls(argv[1]);
  return 0;
}

