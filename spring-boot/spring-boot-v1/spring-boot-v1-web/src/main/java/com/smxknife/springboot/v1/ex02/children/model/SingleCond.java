package com.smxknife.springboot.v1.ex02.children.model;

import java.io.Serializable;

public class SingleCond implements Serializable {

    private String region;

    private String industry;

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getIndustry() {
    return industry;
  }

  public void setIndustry(String industry) {
    this.industry = industry;
  }
}
