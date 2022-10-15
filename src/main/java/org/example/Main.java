package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    Counter counter = new Counter();
    try {
      Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
          for (int i = 0; i < 1000; i++) {
            counter.increment();
          }
        }
      });

      Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
          for (int i = 0; i < 1000; i++) {
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