#include <stdio.h>

int main (void) {
  FILE* input_file = fopen("email.txt", "r");

  char email[100];

  if (input_file != NULL) {
    int rc;
    while ((rc = fscanf(input_file, "%[_a-zA-Z0-9.]@[_a-zA-Z0-9.]\n", email)) != EOF)
    {
      if (rc == 1)
      {
        printf("email is: %s\n", email);
      }
      else
      {
        printf("run into some error, rc = %d!\n", rc);
        break;
      }
    }
    fclose(input_file);
  }
  else {
    printf ("open failed.");
  }
  return 0;
}
