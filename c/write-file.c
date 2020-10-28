#include <stdio.h>
#include <string.h>

int writeFile(FILE *fp) {
  double d = 0.01;
  for (int i = 0; i < 150; i++) {
    fwrite(&d, sizeof(double), 1, fp);
    d += d;
  }
  return 0;
}

int main () {
  FILE *fp;

  /* opening file for reading */
  fp = fopen("double-values.bin" , "w");
  if(fp == NULL) {
    perror("Error opening file");
    return(-1);
  }

  writeFile(fp);

  fclose(fp);

  return(0);
}
