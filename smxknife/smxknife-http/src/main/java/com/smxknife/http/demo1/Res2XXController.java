package com.smxknife.http.demo1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class Res2XXController {

    @RequestMapping("/2xx")
    @ResponseBody
    public String res2xx(HttpServletRequest request, HttpServletResponse response) {
        return "2xx";
    }
}
