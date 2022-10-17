package org.example;

public class HW_part10 {

  public static void main(String[] args) {
    MFU mfu = new MFU();
    new Thread(new Runnable() {
      @Override
      public void run() {
        mfu.scan(5);
      }
    }).start();
    new Thread(new Runnable() {
      @Override
      public void run() {
        mfu.print(4);
      }
    }).start();
    new Thread(new Runnable() {
      @Override
      public void run() {
        mfu.scan(5);
      }
    }).start();
    new Thread(new Runnable() {
      @Override
      public void run() {
        mfu.print(4);
      }
    }).start();
  }

}
