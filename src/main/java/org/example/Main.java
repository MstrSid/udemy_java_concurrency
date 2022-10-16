package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    Counter counter = new Counter();
    long before = System.currentTimeMillis();
    int barrier = 100_000_000;
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

      Thread thread3 = new Thread(new Runnable() {
        @Override
        public void run() {
          for (int i = 0; i < barrier; i++) {
            counter.increment2();
          }
        }
      });

      Thread thread4 = new Thread(new Runnable() {
        @Override
        public void run() {
          for (int i = 0; i < barrier; i++) {
            counter.decrement2();
          }
        }
      });

      thread1.start();
      thread2.start();
      thread3.start();
      thread4.start();
      thread1.join();
      thread2.join();
      thread3.join();
      thread4.join();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    System.out.println(counter.getValue());
    System.out.println(counter.getValue2());
    long after = System.currentTimeMillis();
    System.out.println(after - before);
  }
}