import java.util.concurrent.*;

public class Executor {
  private final ExecutorService _executor;
  private final ExecutorService _executorWithEH;
  private int threads = 10;
  static {
    // Replace the default uncaught exception handler
    Thread.UncaughtExceptionHandler OLD_HANDLER = Thread.getDefaultUncaughtExceptionHandler();
    Thread.setDefaultUncaughtExceptionHandler((Thread t, Throwable e) -> {
      System.out.println(String.format("Thread %s terminated abnormally", t.getName()));
      // e.printStackTrace();
      // Resume the old behavior
      if (OLD_HANDLER != null) {
        OLD_HANDLER.uncaughtException(t, e);
      }
    });
  }

  public Executor() {
    this._executorWithEH = Executors.newFixedThreadPool(threads, new TaskThreadFactory());
    this._executor = Executors.newFixedThreadPool(threads);
  }
  public void start() {
    while (true) {
      Runnable task = () -> {
        try {
          Thread.sleep(100);
        } catch(Exception e) {

        }
        if (System.currentTimeMillis() % 3 == 1) {
          throw new RuntimeException("...exception...");
        }
      };
      this._executor.execute(task);
    }
  }
  public static void main(String[] args) {
    Executor ex = new Executor();
    ex.start();
  }
  public static class TaskThreadFactory implements ThreadFactory, Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
      System.out.println("TF => Uncaught Exception occurred on thread: " + t);
      // e.printStackTrace();
    }
    @Override
    public Thread newThread(Runnable r) {
      final Thread thread = new Thread(r);
      thread.setUncaughtExceptionHandler(this);
      return thread;
    }
  }
}
