package com.smxknife.http.demo1;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/path/{scope}/")
public class PathVariableController {

    @RequestMapping("index")
    public String index(@PathVariable("scope") String scope) {
        return scope;
    }
}
