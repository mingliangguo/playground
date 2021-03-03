#include <stdio.h>
#include <string.h>
#include <math.h>

int readfile(FILE* fin, FILE* fout) {
  fseek(fin, 0, SEEK_SET);
  double values[100];
  int len = 0;

  printf("begin to read file ...\n");
  while((len = fread(values, sizeof(double), 100, fin)) != 0) {
    printf("read %d values\n", len);
    for (int i = 0; i < len; i++) {
      double val = cos(values[i]);
      fprintf(fout, "%0.4f : %.4f\n", values[i], val);
    }
  }
  return 0;
}

int readByFgets(FILE *fp) {
   char str[10];
   if( fgets (str, 10, fp)!=NULL ) {
     for (int i = 0; i < 10; i++) {
       printf("str[i] = %c\n", str[i]);
     }

     if (str[9] == '\0') {
       printf("str[9] is '\\0'\n");
     }
      /* writing content to stdout */
      //puts(str);
   }
   return 0;
}

int main () {
   FILE *fp;
   FILE *fout;

   /* opening file for reading */
   fp = fopen("double-values.bin" , "r");
   fout = fopen("double-values.txt" , "w");
   if(fp == NULL || fout == NULL) {
      perror("Error opening file");
      return(-1);
   }

   readfile(fp, fout);

   fclose(fp);
   fclose(fout);
   
   return(0);
}
