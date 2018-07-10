package com.smxknife.java.ex8.executor;

import java.util.concurrent.Executors;

public class Demo8Main {
  public static void main(String[] args) {
    while (true) {
      Executors.newSingleThreadExecutor().execute(new Task());
      try {
        Thread.sleep(5000L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
