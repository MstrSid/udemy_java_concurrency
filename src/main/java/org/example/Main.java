package org.example;

public class Main {

  public static void main(String[] args) {
    BlockingQueue blockingQueue = new BlockingQueue();
    new Thread(new Runnable() {
      @Override
      public void run() {
        int counter = 1;
        while (true) {
          System.out.println("Calls: " + counter++);
          Runnable task = blockingQueue.take();
          if (task != null) {
            new Thread(task).start();
          }
        }
      }
    }).start();

    for (int i = 0; i < 10; i++) {
      final int index = i;
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      blockingQueue.add(new Runnable() {
        @Override
        public void run() {
          try {
            Thread.sleep(1000);
            System.out.println("Task" + index);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        }
      });
    }

  }
}