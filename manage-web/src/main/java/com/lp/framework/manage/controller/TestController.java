package com.lp.framework.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @GetMapping("/test")
    @ResponseBody
    public String test(){
        return "success";
    }

    @GetMapping("/table")
    public String table(){
        return "test/table";
    }
}
