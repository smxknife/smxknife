package com.smxknife.springboot.v1.ex02.children.child1.web.controller;

import com.smxknife.springboot.v1.ex02.children.model.ConditionModel;
import com.smxknife.springboot.v1.ex02.children.model.SingleCond;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/index/test")
    public String indexTest() {
    	return "index";
    }

    @GetMapping("/index2")
    public String index2() {
    	return "index2";
    }

    @PostMapping("/index")
    @ResponseBody
    public String post(@RequestBody String json) {
        return json;
    }

    @PostMapping("/cond")
    @ResponseBody
    public Object cond(ConditionModel model) {
        System.out.println("xxxx");
        if (Objects.nonNull(model)) {
            model.getRegions().forEach(System.out::println);
            model.getIndustrys().forEach(System.out::println);
        }
        return "success";
    }

    @PostMapping("/list")
    @ResponseBody
    public Object list(@RequestParam("conds[]") String[] conds) {
        if (Objects.nonNull(conds)) {
            Arrays.asList(conds).forEach(System.out::println);
        }
        return "list";
    }

    @PostMapping("/list/obj")
    @ResponseBody
    public Object listObj(@RequestBody List<SingleCond> singleConds) {
        if (CollectionUtils.isEmpty(singleConds)) {
            singleConds.forEach(System.out::println);
        }
        return "listObj";
    }
}
