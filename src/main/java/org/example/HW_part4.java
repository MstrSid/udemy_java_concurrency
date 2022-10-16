package org.example;

public class HW_part4 {

  public static void main(String[] args) {
    ATM atm = new ATM();
//    Thread client1 = new Thread(new Runnable() {
//      @Override
//      public void run() {
//        atm.cashOut("Mike", 500.5);
//      }
//    });
//    Thread client2 = new Thread(new Runnable() {
//      @Override
//      public void run() {
//        try {
//          client1.join();
//          atm.cashOut("Olga", 2500);
//        } catch (InterruptedException e) {
//          throw new RuntimeException(e);
//        }
//      }
//    });
//    Thread client3 = new Thread(new Runnable() {
//      @Override
//      public void run() {
//        try {
//          client2.join();
//          atm.cashOut("Bob", 1500.55);
//        } catch (InterruptedException e) {
//          throw new RuntimeException(e);
//        }
//      }
//    });
    Thread client1 = new Thread(new Runnable() {
      @Override
      public void run() {
        atm.cashOut("Mike", 500.5);
      }
    });
    Thread client2 = new Thread(new Runnable() {
      @Override
      public void run() {
        atm.cashOut("Olga", 2500);
      }
    });
    Thread client3 = new Thread(new Runnable() {
      @Override
      public void run() {
        atm.cashOut("Bob", 1500.55);
      }
    });
    client1.start();
    client2.start();
    client3.start();
  }
}
