#include<stdio.h>    
#include<stdlib.h>    
#include<string.h>    
#include<pthread.h>
#include <unistd.h>

#define NUM_THREADS 5

struct thread_data
{
  int  thread_id;
  char data[50];
  unsigned int seed;
};


void *hello(void *threadarg)
{
  struct thread_data *myData;

  myData = (struct thread_data *) threadarg;
  printf("thread[%d], hello: %s\n", myData->thread_id, myData->data);
  unsigned int sleep = rand_r(&myData->seed) % 100;
  printf("thread[%d], sleep - %d\n", myData->thread_id, sleep);
  usleep(sleep);
  return NULL;
}

int main ()
{
  pthread_t threads[NUM_THREADS];

  struct thread_data td[NUM_THREADS];
  
  int rc, i;

  unsigned int seed = (unsigned) time(NULL);
  for(i = 0; i < NUM_THREADS; i++)
  {
    td[i].thread_id = i;
    td[i].seed = seed++;
    sprintf(td[i].data, "This is message - [%d]", i);

    rc = pthread_create(&threads[i], NULL,

        hello, (void *)&td[i]);
    // pthread_join(threads[i], NULL);

    if (rc){

      printf("Error:unable to create thread, %d\n", rc);

      exit(-1);
    }
  }
  for(i = 0; i < NUM_THREADS; i++)
  {
    pthread_join(threads[i], NULL);
  }

  pthread_exit(NULL);
  return 0;
}
