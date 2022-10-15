package org.example;

import java.util.Arrays;

public class HW_part2 {

  private static final int SIZE = 10000000;
  private static final int HALF_SIZE = SIZE / 2;

  public static void main(String[] args) {
    startTimer();
    withoutConcurrency();
    withConcurrency();
  }

  private static void startTimer() {
    Thread timer = new Thread(new Runnable() {
      @Override
      public void run() {
        int seconds = 0;
        try {
          while (true) {
            System.out.println(seconds++);
            Thread.sleep(1000);
          }
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });
    timer.setDaemon(true);
    timer.start();
  }

  private static void withConcurrency() {
    float[] array = new float[SIZE];
    long before = System.currentTimeMillis();
    Arrays.fill(array, 1);
    float[] temp1 = new float[HALF_SIZE];
    float[] temp2 = new float[HALF_SIZE];
    System.arraycopy(array, 0, temp1, 0, HALF_SIZE);
    System.arraycopy(array, HALF_SIZE, temp2, 0, HALF_SIZE);
    try {
      Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
          for (int i = 0; i < temp1.length; i++) {
            float f = (float) i;
            temp1[i] = (float) (array[i] * Math.sin(0.2f + f / 5) * Math.cos(0.2f + f / 5)
                * Math.cos(
                0.4f + f / 2));
          }
        }
      });
      Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
          for (int i = 0; i < temp2.length; i++) {
            float f = (float) i;
            temp2[i] = (float) (array[i] * Math.sin(0.2f + f / 5) * Math.cos(0.2f + f / 5)
                * Math.cos(
                0.4f + f / 2));
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
    System.arraycopy(temp1, 0, array, 0, HALF_SIZE);
    System.arraycopy(temp2, 0, array, HALF_SIZE, HALF_SIZE);
    long after = System.currentTimeMillis();
    System.out.println("withConcurrency time: " + (after - before));
  }

  private static void withoutConcurrency() {
    float[] array = new float[10000000];
    long before = System.currentTimeMillis();
    Arrays.fill(array, 1);
    for (int i = 0; i < array.length; i++) {
      float f = (float) i;
      array[i] = (float) (array[i] * Math.sin(0.2f + f / 5) * Math.cos(0.2f + f / 5) * Math.cos(
          0.4f + f / 2));
    }
    long after = System.currentTimeMillis();
    System.out.println("withoutConcurrency time: " + (after - before));
  }

}
