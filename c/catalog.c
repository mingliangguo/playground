#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
  int id;
  char title[20];
  char author[20];
} Book;


void createBooks();
void createBookArray();
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

  for (int i = 0; i < initCapacity; i++) {
    Book book = books[i];
    printf("Books - [%d], title: %s, author: %s\n", book.id, book.title, book.author);
  }
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

