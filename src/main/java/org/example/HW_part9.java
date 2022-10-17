package org.example;

public class HW_part9 {

  private static final Object lock = new Object();
  private static String nextLetter = "A";

  public static void main(String[] args) {

    new Thread(new Runnable() {
      @Override
      public void run() {
        synchronized (lock) {
          for (int i = 0; i < 5; i++) {
            while (!nextLetter.equals("A")) {
              try {
                lock.wait();
              } catch (InterruptedException e) {
                throw new RuntimeException(e);
              }
            }
            System.out.println("A");
            lock.notifyAll();
            nextLetter = "B";
          }
        }
      }
    }).start();

    new Thread(new Runnable() {
      @Override
      public void run() {
        synchronized (lock) {
          for (int i = 0; i < 5; i++) {
            while (!nextLetter.equals("B")) {
              try {
                lock.wait();
              } catch (InterruptedException e) {
                throw new RuntimeException(e);
              }
            }
            System.out.println("B");
            lock.notifyAll();
            nextLetter = "C";
          }
        }
      }
    }).start();

    new Thread(new Runnable() {
      @Override
      public void run() {
        synchronized (lock) {
          for (int i = 0; i < 5; i++) {
            while (!nextLetter.equals("C")) {
              try {
                lock.wait();
              } catch (InterruptedException e) {
                throw new RuntimeException(e);
              }
            }
            System.out.println("C");
            lock.notifyAll();
            nextLetter = "A";
          }
        }
      }
    }).start();
  }
}
