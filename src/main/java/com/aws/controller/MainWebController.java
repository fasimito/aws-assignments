package com.aws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainWebController {

    private static final Logger logger= LoggerFactory.getLogger(MainWebController.class);

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        logger.info("start access the short URL service");
        return "index";
    }

}
