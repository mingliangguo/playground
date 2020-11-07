#include <stdio.h>
#include <stdlib.h>

void printIntChar(unsigned char* data, size_t n) {
  printf("size is: %zu\n", n);
  for (int i = 0; i < n; i++) {
    printf("0x%02X\n", data[i]);
  }
  printf("\n");
}
void toBigEndian(unsigned char * buffer, unsigned int num) {
  char * p = (char *) &num;
  buffer[0] = p[3];
  buffer[1] = p[2];
  buffer[2] = p[1];
  buffer[3] = p[0];
}

void toBigEndian1(unsigned char * buffer, unsigned int value) {
  buffer[0] = value >> 24;
  buffer[1] = value >> 16;
  buffer[2] = value >> 8;
  buffer[3] = value;
}
int main() {
  // unsigned int num = 0xAABBCCDD;
  unsigned int num = 224;
  printf("print %d:", num);
  printIntChar((unsigned char *)&num, sizeof(num));

  unsigned char buffer[4];
  toBigEndian1(buffer, num);
  printIntChar(buffer, sizeof(buffer));
}
