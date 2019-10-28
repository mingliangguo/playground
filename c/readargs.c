#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(int argc, char* argv[]) {
  int longest = 0;
  for (int i = 0; i < argc; i++) {
    printf("argv[%d] = %s\n", i, argv[i]);
    longest = longest > strlen(argv[i]) ? longest : strlen(argv[i]);
  }
  printf("longest length is: %d\n", longest);

  char (*words)[longest + 1] = malloc( sizeof( char[longest + 1] ) * argc) ;
  // char** words = (char **)malloc(argc * sizeof(char *));
  for (int i = 0; i < argc; i++) {
    // words[i] = (char*) malloc((longest + 1) * sizeof(char));
    strcpy(words[i], argv[i]);
  }
  for (int i = 0; i < argc; i++) {
    printf("words[%d] = %s\n", i, words[i]);
  }
}
