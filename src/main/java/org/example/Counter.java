package org.example;

public class Counter {

  private Object monitor = new Object();
  private Object monitor2 = new Object();
  private int value;
  private int value2;

  public int getValue() {
    return value;
  }

  public void increment() {
    synchronized (monitor) {
      value++;
    }
  }

  public void decrement() {
    synchronized (monitor) {
      value--;
    }
  }

  public int getValue2() {
    return value2;
  }

  public void increment2() {
    synchronized (monitor2) {
      value2++;
    }
  }

  public void decrement2() {
    synchronized (monitor2) {
      value2--;
    }
  }
}
