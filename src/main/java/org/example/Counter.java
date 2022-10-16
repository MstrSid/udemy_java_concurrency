package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

  private final AtomicInteger value = new AtomicInteger();

  public int getValue() {
    return value.intValue();
  }

  public void increment() {
    value.getAndIncrement();
  }

  public void decrement() {
    value.getAndDecrement();
  }
}
