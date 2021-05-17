#define _GNU_SOURCE             /* See feature_test_macros(7) */
#include <sched.h>
#include <stdio.h>
#include <stdio.h>
#include <string.h>


int main () {
  const char haystack[20] = "TutorialsPoint";
  const char needle[10] = "Point";
  char *ret;

  ret = strstr(haystack, needle);

  printf("The substring is: %s\n", ret);
  unsigned int cpu = 0;
  unsigned int node = 0;
  getcpu(&cpu, &node);
  printf("cpu is: %d, node is: %d\n", cpu, node);
  return 0;
}
