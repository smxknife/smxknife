package com.smxknife.java.ex14;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author smxknife
 * 2018/9/13
 */
public class DirDemo {
  public static void main(String[] args) throws IOException {
    System.out.println(System.getProperty("user.dir"));
    Path path = Paths.get(System.getProperty("user.dir"), "aaa.txt");
    Files.exists(path);
    System.out.println(path.toString());
    System.out.println(File.separator);
    String[] split = path.toString().split(File.separator);
    for (String p : split) {
      System.out.println(p);
    }

    String field = "abcDac123A1";
    Pattern pattern = Pattern.compile("([A-Z])|([0-9]+)");
    StringBuffer sb = new StringBuffer();
    Matcher matcher = pattern.matcher(field);
    while (matcher.find()) {
      matcher.appendReplacement(sb, "_" + matcher.group().toLowerCase());
    }
    matcher.appendTail(sb);
    System.out.println(sb.toString());
  }
}
