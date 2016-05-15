#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <dirent.h>
#include <err.h>
#include <set>
#include <string>

#define MAXLEN 1024

std::set<std::string> set;

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
  fclose(f);
}

int checkFileType(char *file, char *type) {
  char *pos = strrchr(file, '.');
  return pos && !strcmp(pos + 1, type);
}

char *getFileName(char *file) {
  static char buf[MAXLEN];
  char *p = buf;
  while (*file != '.' && *file != 0) *p++ = *file++;
  *p = 0;
  return buf;
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
    // regular file and .gcov
    if (ent->d_type == 8 && checkFileType(ent->d_name, "gcov") &&
        set.find(getFileName(ent->d_name)) != set.end()) {
      buf[len] = 0;
      parse(strcat(buf, ent->d_name));
    }
  }
}

void lsgcda(char *path) {
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
    if (ent->d_type == 8 && checkFileType(ent->d_name, "gcda")) {
      buf[len] = 0;
      set.insert(getFileName(ent->d_name));
    }
  }
}

int main(int argc, char **argv) {
  lsgcda(argv[1]);
  ls(argv[1]);
  return 0;
}

