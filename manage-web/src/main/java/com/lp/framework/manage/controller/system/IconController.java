package com.lp.framework.manage.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/icon")
public class IconController {

    @GetMapping("/index")
    public String index(){
        return "system/icon/iconIndex";
    }
}
