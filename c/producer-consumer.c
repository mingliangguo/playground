#include<stdio.h>
#include<stdlib.h>
#define SIZE 10

typedef struct {
  char* name;
  int id;
} Item;

void enQueue(Item * it);
Item* deQueue();
void freeItem(Item* it);
void print();

Item* queue[SIZE];
int front = -1, rear = -1;

int id = 0;

int main()
{
  int choice;
  while(1){
    printf("\n\n***** MENU *****\n");
    printf("1. Insertion\n2. Deletion\n3. print\n4. Exit");
    printf("\nEnter your choice: ");
    scanf("%d",&choice);
    switch(choice){
      case 1: printf("Enter the value to be insert: ");
              char* str = (char*)malloc(50*sizeof(char));
              scanf("%s", str);
              Item* it = (Item *)malloc(sizeof(Item));
              it->name = str;
              it->id = id++;
              enQueue(it);
              break;
      case 2: {
                Item* it = deQueue();
                printf("\nDequeued : [id: %d, name: %s]\n", it->id, it->name);
                freeItem(it);
                break;
              }
      case 3: print();
              break;
      case 4: exit(0);
      default: printf("\nWrong selection!!! Try again!!!");
    }
  }
}
void enQueue(Item* it){
  if(rear == SIZE-1)
    printf("\nQueue is Full!!! Insertion is not possible!!!");
  else{
    if(front == -1)
      front = 0;
    rear++;
    queue[rear] = it;
    printf("\nInsertion success!!!");
  }
}
Item* deQueue(){
  if(front == rear) {
    printf("\nQueue is Empty!!! Deletion is not possible!!!");
    return NULL;
  }

  else{
    Item* it = queue[front];
    printf("\nDeleted : [id: %d, name: %s]\n", it->id, it->name);
    front++;
    if(front == rear)
      front = rear = -1;
    return it;
  }
}
void print(){
  if(rear == -1)
    printf("\nQueue is Empty!!!");
  else{
    int i;
    printf("\nQueue elements are:\n");
    for(i=front; i<=rear; i++)
      printf("item[id=%d, name=%s]\t",queue[i]->id, queue[i]->name);
  }
}
void freeItem(Item *it) {
  free(it->name);
  free(it);
}
