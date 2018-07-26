package com.smxknife.springboot.v1.ex02.children.model;

import java.io.Serializable;
import java.util.List;

public class ConditionModel implements Serializable {

    List<String> regions;

    List<String> industrys;

  public List<String> getRegions() {
    return regions;
  }

  public void setRegions(List<String> regions) {
    this.regions = regions;
  }

  public List<String> getIndustrys() {
    return industrys;
  }

  public void setIndustrys(List<String> industrys) {
    this.industrys = industrys;
  }
}
