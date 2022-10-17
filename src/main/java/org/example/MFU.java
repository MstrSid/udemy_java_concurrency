package org.example;

public class MFU {

  private final Object monitorPrint = new Object();
  private final Object monitorScan = new Object();

  public void print(int pagesCount) {
    synchronized (monitorPrint) {
      for (int i = 0; i < pagesCount; i++) {
        final int index = i;
        try {
          Thread.sleep(500);
          System.out.println("Printing page " + index);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  public void scan(int pagesCount) {
    synchronized (monitorScan) {
      for (int i = 0; i < pagesCount; i++) {
        final int index = i;
        try {
          Thread.sleep(500);
          System.out.println("Scanning page " + index);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }
}
