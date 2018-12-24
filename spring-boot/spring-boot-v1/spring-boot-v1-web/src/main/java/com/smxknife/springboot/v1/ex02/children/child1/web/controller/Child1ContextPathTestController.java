package com.smxknife.springboot.v1.ex02.children.child1.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smxknife
 * 2018-12-24
 */
@RestController
@RequestMapping("/ctx/path")
public class Child1ContextPathTestController {

    @GetMapping()
    public String test() {
        return "hhhhh";
    }
}
