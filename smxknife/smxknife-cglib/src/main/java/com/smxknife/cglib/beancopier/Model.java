package com.smxknife.cglib.beancopier;

public class Model {
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Model{" +
      "name='" + name + '\'' +
      '}';
  }
}
