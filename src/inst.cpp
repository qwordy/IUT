#include "inst.h"
#include <dirent.h>
#include <err.h>
#include <stdio.h>
#include <stddef.h>
#include <string.h>
#include <string>

// .c .cc .cpp .h .hpp
bool isCFile(char *file) {
  char *pos;
  const char *suffixes[] = {"c", "cc", "cpp", "h", "hpp"};

  pos = strrchr(file, '.');
  
  if (pos != NULL) {
    for (const char *suffix : suffixes) {
      if (strcmp(pos + 1, suffix) == 0)
        return true;
    }
  }

  return false;
}

int instFile(const char *path) {
  printf("Inst file %s\n", path);
}

int traverse(const char *path) {
  DIR *dir;
  dirent *ent;

  if ((dir = opendir(path)) == NULL)
    err(-1, "can't open %s", path);

  printf("Enter %s\n", path);

  while ((ent = readdir(dir)) != NULL) {
    if (strcmp(ent->d_name, "..") == 0 || strcmp(ent->d_name, ".") == 0)
      continue;

    printf("%s\n", ent->d_name);

    std::string s0(path);
    std::string s1(ent->d_name);
    s0 += "/" + s1;

    if (ent->d_type == 4) { // Directory
      traverse(s0.c_str());
    } else if (ent->d_type == 8) {  // Regular file
      if (isCFile(ent->d_name))
        instFile(s0.c_str());
    }
  }

  closedir(dir);
  printf("Leave %s\n", path); 
  return 0;
}

int inst() {
  traverse("/home/yfy/iutc/cctz2/src");
  return 0;
}

