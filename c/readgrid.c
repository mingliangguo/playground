#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void readGrid(int row, int col, int grid[row][col]);
void readInput(int row, int col, int grid[row][col]);
void printGrid(int row, int col, int grid[row][col]);

int main(int argc, char** argv) {
  if (argc < 3) {
    printf("usage: readgrid 3 3!");
    exit(-1);
  }
  int row = atoi(argv[1]), col = atoi(argv[2]);
  // printf("please input the row and column of the grid, separate by space\n");
  // scanf("%d %d\n", &row, &col);
  int grid[row][col];
  memset(grid, 0, row * col * sizeof(int));
  // readGrid(row, col, grid);
  readInput(row, col, grid);
  printGrid(row, col, grid);
  return 0;
}

void readGrid(int row, int col, int grid[row][col]) {
  int i=0, j=0;
  for (i = 0; i < row; ++i) {
    char line[1000];

    j = 0;
    if (fgets(line, 1000, stdin) != NULL) {
      printf("line is: %s\n", line);
      char *p = strtok (line, " ");
      while (p != NULL && j < col) {
          grid[i][j++] = atoi(p);
          p = strtok (NULL, " ");
      }
    }
  }

  for (i = 0; i < row; ++i) {
    for(j = 0; j < col; j++) {
      printf("%d ", grid[i][j]);
    }
    printf("\n");
  }
}
void readInput(int row, int col, int grid[row][col]) {
  int num;
  char c;
  int i=0, j=0;
  for (i = 0; i < row; ++i) {
    char line[1000];

    j = 0;
    printf ("num is: %d, char is: %c; ", num, c);
    do {
      scanf("%d%c", &num, &c);
      grid[i][j++] = num;
    } while (c != '\n' && j < col);
    printf("\n");
  }
}

void printGrid(int row, int col, int grid[row][col]){
  for (int i = 0; i < row; ++i) {
    for(int j = 0; j < col; j++) {
      printf("%d ", grid[i][j]);
    }
    printf("\n");
  }
}
