package com.smxknife.java.ex19;

import java.util.Arrays;

/**
 * @author smxknife
 * 2018/11/21
 */
public class ForeachDemo {
  public static void main(String[] args) {
    Arrays.asList(1,3,5).forEach(item -> {
      System.out.println(item);
      return;
    });
  }
}
