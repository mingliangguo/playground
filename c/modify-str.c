#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void removeRange(char str[], int len, int start, int end) {
  if (start > end)
    return;
  if (start < 0)
    start = 0;

  if (start >= 0 && end >= len) {
      str[start] = '\0';
      return;
  }
  // memmove(str + start, str + end + 1, len - end);
  memmove(&str[start], &str[end + 1], len - end);
  str[len - (end - start + 1)] = '\0';
}
int main(int argc, char **argv) {
  char str[] = "hello world!";
  printf("before is: %s\n", str);
  removeRange(str, strlen(str), 2, 5);
  printf("after is: %s\n", str);

  int l = 100;
  printf("sizeof(len) is: %lu\n", sizeof(l));
}


