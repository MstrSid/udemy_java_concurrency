package org.example;

import java.util.concurrent.CyclicBarrier;

public class Main {

  public static void main(String[] args) {
    CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
    for (int i = 0; i < 10; i++) {
      new Thread(new Runnable() {
        @Override
        public void run() {
          long millis = (long) (Math.random() * 5000 + 1000);
          String name = Thread.currentThread().getName();
          System.out.println(name + ": data is preparing.");
          try {
            Thread.sleep(millis);
            System.out.println(name + ": data is ready.");
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          try {
            cyclicBarrier.await();
          } catch (Exception e) {
            e.printStackTrace();
          }
          System.out.println(name + ": continue work.");
        }
      }).start();
    }
  }

  public static void workWithFilesystem() {
    String name = Thread.currentThread().getName();
    System.out.println(name + " started working with filesystem.");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(name + " finished working with filesystem.");
  }
}