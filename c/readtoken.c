#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void tokeninze(const char * line);

int main(int argc, char** argv) {
  const char * line = "id\ttest\taaa\tff\tdd";
  tokeninze(line);
  return 0;
}

void tokeninze(const char * line) {
  const char * delimiter = "\t";
  printf("line is: %s\n", line);
  char* toks[100];
  char * text = strdup(line);
  int count = 0;
  char *p = strtok (text, delimiter);
  while (p != NULL) {
      toks[count++] = p;
      p = strtok (NULL, delimiter);
  }

  for (int i = 0; i < count; ++i) {
    printf("token[%d]: %s\n", i, toks[i]);
  }
}

