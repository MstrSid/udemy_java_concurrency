package org.example;

public class Main {

  public static void main(String[] args) {
    Counter counter = new Counter();
    int barrier = 1000;
    try {
      Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
          for (int i = 0; i < barrier; i++) {
            counter.increment();
          }
        }
      });

      Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
          for (int i = 0; i < barrier; i++) {
            counter.decrement();
          }
        }
      });
      thread1.start();
      thread2.start();
      thread1.join();
      thread2.join();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    System.out.println(counter.getValue());
  }
}