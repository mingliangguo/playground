public class DaemonThreadExample extends Thread{

  public void run(){  
    while (true) {
      try {
        Thread.sleep(100);
      } catch(Exception e) {

      }
      // Checking whether the thread is Daemon or not
      if(Thread.currentThread().isDaemon()){ 
        System.out.println("Daemon thread executing");  
      }  
      else{  
        System.out.println("user(normal) thread executing");  
      }  
      if (System.currentTimeMillis() % 2 == 1) {
        throw new RuntimeException("hahaha");
      }

    }
  }  
  public static void main(String[] args){  
    /* Creating two threads: by default they are 
     * user threads (non-daemon threads)
     */
    DaemonThreadExample t1=new DaemonThreadExample();
    DaemonThreadExample t2=new DaemonThreadExample();   

    //Making user thread t1 to Daemon
    t1.setDaemon(true);

    //starting both the threads 
    t1.start(); 
    t2.start();  
  } 
}

