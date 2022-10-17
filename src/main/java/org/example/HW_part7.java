package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HW_part7 {

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    CountDownLatch countDownLatch = new CountDownLatch(3);
    long before = System.currentTimeMillis();
    executorService.execute(new Runnable() {
      @Override
      public void run() {
        sumOdd();
        countDownLatch.countDown();
      }
    });
    executorService.execute(new Runnable() {
      @Override
      public void run() {
        sumOddSeven();
        countDownLatch.countDown();
      }
    });
    executorService.execute(new Runnable() {
      @Override
      public void run() {
        getRandomWithSum();
        countDownLatch.countDown();
      }
    });
    try {
      countDownLatch.await();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    executorService.shutdown();
    long after = System.currentTimeMillis();
    System.out.println("Time: " + (after - before));
  }

  private static void sumOdd() {
    long sum = 0;
    for (int i = 0; i < 10000000; i++) {
      if (i % 2 == 0) {
        sum += i;
      }
    }
    System.out.println("sumOdd: "+sum);
  }

  private static void sumOddSeven() {
    long sum = 0;
    for (int i = 0; i < 10000000; i++) {
      if (i % 7 == 0) {
        sum += i;
      }
    }
    System.out.println("sumOddSeven: "+sum);
  }

  private static void getRandomWithSum() {
    List<Integer> numbers = new ArrayList<>();
    for (int i = 0; i < 10000; i++) {
      numbers.add((int) (Math.random() * 10000));
    }
    int count = 0;
    for (int i : numbers) {
      if (i % 2 == 0) {
        count++;
      }
    }
    System.out.println("getRandomWithSum: "+count);
  }

}
