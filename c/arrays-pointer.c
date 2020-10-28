#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char** argv) {
  int len = 9;
  char lines[9][100] = {
    "abc",
    "def",
    "ghi",
    "jkl",
    "mno",
    "pqr",
    "stu",
    "vwx",
    "yz"
  };


  int start = 3, stop = 5;

  int gap = stop - start + 1;
  for (int i = start; i + gap < len; i++) {
    // strcpy(lines[i], lines[i + gap]);
    lines[i] = lines[i + gap];
  }

  for (int i = 0; i < len; i++) {
    printf("lines[%d] = %s\n", i, lines[i]);
  }

//  char *temp[100];
//  int j = 0;
//  for (int i = 0; i < 9; i++) {
//    if (i < start || i > stop) {
//      temp[j] = lines[i];
//      printf("temp[%d] = %s\n", j, temp[j]);
//      j++;
//    }
//  }
}
