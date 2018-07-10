package com.smxknife.springboot.v2.controlleraop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class HelloController {

  @RequestMapping("")
  public String index() {
    return "index";
  }
}
