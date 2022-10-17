package org.example;

public class Account {

  private int amount1;
  private int amount2;
  private final Object monitor1 = new Object();
  private final Object monitor2 = new Object();

  public Account(int amount1, int amount2) {
    this.amount1 = amount1;
    this.amount2 = amount2;
  }

  public void transferFrom1to2(int amount) {
    synchronized (monitor1) {
      try {
        Thread.sleep(2000);
        if (amount <= amount1) {
          System.out.println("Money on amount1 is enough.");
          synchronized (monitor2) {
            Thread.sleep(2000);
            amount2 += amount;
            amount1 -= amount;
          }
        } else {
          System.out.println("Not enough money");
        }
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public void transferFrom2to1(int amount) {
    synchronized (monitor2) {
      try {
        Thread.sleep(2000);
        if (amount <= amount2) {
          System.out.println("Money on amount2 is enough.");
          synchronized (monitor1) {
            Thread.sleep(2000);
            amount1 += amount;
            amount2 -= amount;
          }
        } else {
          System.out.println("Not enough money");
        }
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
