package com.smxknife.http.demo1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ResOptionsController {

    @RequestMapping(value = "/ops", method = {RequestMethod.GET, RequestMethod.HEAD})
    @ResponseBody
    public String opts() {
        return "opts";
    }
}
