#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
  int id;
  char title[20];
  char author[20];
} Book;


void printBooks(Book* books, size_t size);
void createBooks();
void createBookArray();
int bookComparator(const void * p1, const void * p2) {
  Book *b1 = (Book *)p1;
  Book *b2 = (Book *)p2;
  return b2->id - b1->id;
}
int main() {
  // createBooks();
  createBookArray();
}


void createBookArray() {
  int initCapacity = 5;
  Book * books = (Book *)malloc(sizeof(Book));
  for (int i = 0; i < initCapacity; i++) {
    books[i].id = i;
    sprintf(books[i].title, "title - %d", i);
    sprintf(books[i].author, "author - %d", i);
  }

  printf("before sort: \n");
  printBooks(books, initCapacity);
  qsort(books, initCapacity, sizeof(Book), bookComparator);
  printf("after sort: \n");
  printBooks(books, initCapacity);
  free(books);
}

void createBooks() {
  int initCapacity = 5;
  Book * books = (Book *)malloc(sizeof(Book) * initCapacity);
  Book *book = books;
  for (int i = 0; i < initCapacity; i++) {
    book->id = i;
    sprintf(book->title, "title - %d", i);
    sprintf(book->author, "author - %d", i);
    book++;
  }

  book = books;
  for (int i = 0; i < initCapacity; i++) {
    printf("Books - [%d], title: %s, author: %s\n", book->id, book->title, book->author);
    book++;
  }
  free(books);
}

void printBooks(Book* books, size_t size) {
  Book* book = books;
  for (int i = 0; i < size; i++) {
    printf("Books - [%d], title: %s, author: %s\n", book->id, book->title, book->author);
    book++;
  }
}
