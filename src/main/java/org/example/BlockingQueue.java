package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue {

  private final Queue<Runnable> queue = new LinkedList<>();
  private final Object lock = new Object();

  public void add(Runnable task) {
    synchronized (lock) {
      queue.add(task);
      lock.notify();
    }
  }

  public Runnable take() {
    synchronized (lock) {
      while (queue.isEmpty()) {
        try {
          lock.wait();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
      return queue.poll();
    }
  }

}
