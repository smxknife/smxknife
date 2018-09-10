package com.smxknife.java.ex12;

import java.util.stream.Stream;

/**
 * @author smxknife
 * 2018/9/6
 */
public class StreamIterateDemo {
  public static void main(String[] args) {
    Stream.iterate(2015, seed -> seed + 1).limit(10).forEach(System.out::println);
  }
}
