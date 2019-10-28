#include <stdio.h>

int *min(int *list, int len);

int *min(int *list, int len) {
  int* min = list;
  for (int i = 1; i < len; ++i) {
    if (*min > *list)
        min = list;
    list++;
  }
  return min;
}
int main() {
  int arr[] = {1, 5, 8, 2, 11, 3, 4};
  int *list = arr;
  int *val = min(list, 7);
  printf("min value is : %d\n", *val);
  return 0;
}
