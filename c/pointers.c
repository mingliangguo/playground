#include <stdio.h>

int * smaller(int *p1, int* p2) {
  printf("address: p1: %p, p2: %p\n", p1, p2);
  if (*p1 <= *p2)
    return p1;
  else
    return p2;
}
int main() {
  int a = 30, b = 29;
  int *pa = &a, *pb = &b;
  int *p;
  p = smaller(pa, pb);
  printf("smaller = %d\n", *p);
}
