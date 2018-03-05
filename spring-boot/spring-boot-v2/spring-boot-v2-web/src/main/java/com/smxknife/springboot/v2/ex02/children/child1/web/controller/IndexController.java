package com.smxknife.springboot.v2.ex02.children.child1.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Value("${app.name}")
    private String appName;

    @GetMapping
    public String index() {
        return "child1 index" + appName;
    }
}
