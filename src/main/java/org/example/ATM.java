package org.example;

public class ATM {

  private double cash = 3000.0;
  private final Object lock = new Object();

  public void cashOut(String name, double sum) {
//    synchronized (this) {
    synchronized (lock) {
      System.out.printf("%s come to ATM\n", name);
      System.out.println("Checking cash, wait please...");
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      if (sum <= cash) {
        cash -= sum;
        System.out.printf("%s cash out %.2f\n", name, sum);
      } else {
        System.out.printf("In ATM not enough cash out %s\n", name);
      }
    }
  }
}
