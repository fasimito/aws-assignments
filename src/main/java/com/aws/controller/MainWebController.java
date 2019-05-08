package com.aws.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainWebController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

}
