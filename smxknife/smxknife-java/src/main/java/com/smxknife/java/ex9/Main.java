package com.smxknife.java.ex9;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
  public static void main(String[] args) {

    for (int i = 0; i < 10; i++) {
        System.out.println(new Random().nextInt(2));
    }
    System.out.println("xxxxxxxxx");
    List<Test> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Test t = new Test();
      t.name = "hh" + i;
      t.i = i;
      list.add(t);
    }

//    for (Test test : list) {
//      if (test.i % 3 == 0)
//    }

    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).i % 3 == 0) list.remove(i);
    }

    for (Test test : list) {
      System.out.println(test.name + ", " + test.i);
    }
  }
}
