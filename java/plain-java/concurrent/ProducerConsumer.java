import java.util.concurrent.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ProducerConsumer {
    public static void main(String args[]) {
        BlockingQueue<Integer> sharedQueue = new LinkedBlockingQueue<Integer>(3);
         
        ExecutorService pes = Executors.newFixedThreadPool(2);
        ExecutorService ces = Executors.newFixedThreadPool(2);
          
        pes.submit(new Producer(sharedQueue, 1));
        pes.submit(new Producer(sharedQueue, 2));
        ces.submit(new Consumer(sharedQueue, 1));
        ces.submit(new Consumer(sharedQueue, 2));
         
        pes.shutdown();
        ces.shutdown();
    }
}

/* Different producers produces a stream of integers continuously to a shared queue, 
which is shared between all Producers and consumers */

class Producer implements Runnable {
    private final BlockingQueue<Integer> sharedQueue;
    private int threadNo;
    private Random random = new Random();
    public Producer(BlockingQueue<Integer> sharedQueue,int threadNo) {
        this.threadNo = threadNo;
        this.sharedQueue = sharedQueue;
    }
    @Override
    public void run() {
        // Producer produces a continuous stream of numbers for every 200 milli seconds
        while (true) {
            try {
                int number = random.nextInt(1000);
                // sharedQueue.put(number);
                if (!sharedQueue.offer(number, 1, TimeUnit.SECONDS)) {
                  System.out.printf("Producer Thread[%d] - Unable to add number to queue\n", threadNo);
                } else {
                  System.out.printf("Producer Thread[%d] - Produced: %d, queue size: [%d]\n", threadNo, number, sharedQueue.size());
                }
                Thread.sleep(200);
            } catch (Exception err) {
                err.printStackTrace();
            }
        }
    }
}
/* Different consumers consume data from shared queue, which is shared by both producer and consumer threads */
class Consumer implements Runnable {
    private final BlockingQueue<Integer> sharedQueue;
    private int threadNo;
    public Consumer (BlockingQueue<Integer> sharedQueue,int threadNo) {
        this.sharedQueue = sharedQueue;
        this.threadNo = threadNo;
    }
    @Override
    public void run() {
        // Consumer consumes numbers generated from Producer threads continuously
        while(true){
            try {
                int num = sharedQueue.take();
								Thread.sleep(15000);
                System.out.printf("Consumer Thread[%d] - Consumed: %d, queue size: [%d]\n", threadNo, num, sharedQueue.size());
            } catch (Exception err) {
               err.printStackTrace();
            }
        }
    }   
}
