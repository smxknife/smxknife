package com.smxknife.java.ex7.threadname;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main2 {
  public static void main(String[] args) {
    Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(
      new TestThread(), 10,
      5, TimeUnit.SECONDS);
  }
}
